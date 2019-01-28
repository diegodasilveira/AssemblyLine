package com.silveira.assembly.processor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.silveira.assembly.vo.AssemblyLine;
import com.silveira.assembly.vo.Step;

public class AssemblyLineProcessor {
	
	public List<AssemblyLine> process(List<Step> steps) {
		int assemblyNameCount = 1;
		
		List<Step> stepsRemaining = new ArrayList<>(steps);
		List<AssemblyLine> assemblyLines = new ArrayList<>();
		
		while (!stepsRemaining.isEmpty()) {
			
			AssemblyLine assemblyLine = AssemblyLine.builder().name(AssemblyLine.DEFAULT_NAME + assemblyNameCount).build();
			
			List<Step> morningCombination = getPlanningSteps(stepsRemaining, Step.BEGIN_MORNING, Step.END_MORNING);
			stepsRemaining.removeAll(morningCombination);
			assemblyLine.setSteps(morningCombination);
			
			assemblyLine.getSteps().add(Step.getStepLunchDefault());
			
			List<Step> afternoonCombination = getPlanningSteps(stepsRemaining, Step.BEGIN_AFTERNOON, Step.END_LABORAL_TIME);
			stepsRemaining.removeAll(afternoonCombination);
			assemblyLine.getSteps().addAll(afternoonCombination);
			
			if (!AssemblyRules.verifyBeginLaborActivities(assemblyLine)) {
				needApplyBalanceSteps(assemblyLines, assemblyLine);
			}
			
			assemblyLines.add(assemblyLine);
			assemblyNameCount++;
		}
		
		createLaboralActivities(assemblyLines);
						
		return assemblyLines;
	}
	
	private void createLaboralActivities(List<AssemblyLine> assemblyLines) {
		for (AssemblyLine assemblyLine : assemblyLines) {
			assemblyLine.getSteps().add(Step.getStepLaboralDefault(AssemblyRules.getLastEnd(assemblyLine)));
		}
	}

	private void needApplyBalanceSteps(List<AssemblyLine> assemblyLines, AssemblyLine assemblyLine) {
		
		for (AssemblyLine assemblyLineFull : assemblyLines) {
			LocalTime lastStep = AssemblyRules.getLastEnd(assemblyLineFull);
			
			int minutesOver = (int) Duration.between(Step.BEGIN_LABORAL_TIME, lastStep).toMinutes();
			
			List<Step> stepsToMove = assemblyLineFull.getSteps().stream()
					.filter(step -> verifyCompatibleStepTime(step, minutesOver))
						.collect(Collectors.toList());
			
			if (stepsToMove.isEmpty())
				continue;
			
			int minutesToBalance = minutesOver;
			List<Step> stepsToBalance = new ArrayList<>();
			for (Step step : stepsToMove) {
				if (step.getDurationMinutes() <= minutesToBalance) {
					stepsToBalance.add(step);
					minutesToBalance = minutesToBalance - step.getDurationMinutes();
				} else {
					break;
				}
			}
			
			assemblyLine.getSteps().addAll(stepsToBalance);
			assemblyLineFull.getSteps().removeAll(stepsToBalance);
			
			adjustAfternoonTime(assemblyLine);
			adjustAfternoonTime(assemblyLineFull);
			
			if (AssemblyRules.verifyBeginLaborActivities(assemblyLineFull) && AssemblyRules.verifyBeginLaborActivities(assemblyLine)) {
				return;
			}
		}
	}

	private void adjustAfternoonTime(AssemblyLine assemblyLine) {
		List<Step> stepsAfternoon = assemblyLine.getSteps().stream()
				.filter(step -> step.getBegin().isAfter(Step.BEGIN_LUNCH))
					.collect(Collectors.toList());
		
		assignHour(stepsAfternoon, Step.BEGIN_AFTERNOON);
	}

	private boolean verifyCompatibleStepTime(Step step, int minutesOver) {
		return step.getBegin().isAfter(Step.BEGIN_LUNCH) && step.getDurationMinutes() <= minutesOver;
	}

	private List<Step> getPlanningSteps(List<Step> stepsRemaining, LocalTime begin, LocalTime end) {
		List<Step> steps = getSteps(stepsRemaining, (int) Duration.between(begin, end).toMinutes());
		assignHour(steps, begin);
		
		return steps;
	}

	private void assignHour(List<Step> steps, LocalTime beginHour) {
		LocalTime beginNext = beginHour;
		for (Step step : steps) {
			step.setBegin(beginNext);
			step.setEnd(step.getBegin().plusMinutes(step.getDurationMinutes()));
			
			beginNext = step.getEnd();
		}
	}
	
	private int getSumDuration(List<Step> possibility) {
		return possibility.stream()
						.mapToInt(Step::getDurationMinutes)
						.sum();
	}
	
	private List<Step> getSteps(List<Step> stepsRemaining, int timeFree) {
		List<Step> possibility  = new ArrayList<>();
		List<Step> nearPossibility  = new ArrayList<>();
		int nearSumPossibility = 0;
		
		for (Step step : stepsRemaining) {
			possibility.add(step);
			
			int totalStep = getSumDuration(possibility);
			
			if (totalStep == timeFree) {
				return possibility;
			}
			else if (totalStep > timeFree) {
				possibility.remove(step);
				continue;
			} else {
				if (totalStep > nearSumPossibility) {
					nearSumPossibility = totalStep;
					nearPossibility.clear();
					nearPossibility.addAll(possibility);
				}
			}
			
			for (Step stepOther : stepsRemaining) {
				if (step.equals(stepOther)) {
					continue;
				}
				
				possibility.add(stepOther);

				int total = getSumDuration(possibility);
				
				if (total == timeFree) {
					return possibility;
				} else if (total > timeFree) {
					possibility.remove(stepOther);
					break;
				} else {
					if (total > nearSumPossibility) {
						nearSumPossibility = total;
						nearPossibility.clear();
						nearPossibility.addAll(possibility);
					}
					continue;
				}				
			}	
			possibility.clear();
		}
		
		return nearPossibility;		
	}
	
}
