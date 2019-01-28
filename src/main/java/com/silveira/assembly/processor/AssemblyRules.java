package com.silveira.assembly.processor;

import java.time.LocalTime;

import com.silveira.assembly.vo.AssemblyLine;
import com.silveira.assembly.vo.Step;

public class AssemblyRules {
	
	public static boolean verifyExistInterval(AssemblyLine assemblyLine) {
		LocalTime lastEnd = null;
		boolean existInterval = false;
		
		for (Step step : assemblyLine.getSteps()) {
			if (lastEnd == null) {
				lastEnd = step.getEnd();
			} else {
				if(!lastEnd.equals(step.getBegin())) {
					existInterval = true;
				}
				lastEnd = step.getEnd();
			}
		}
		
		return existInterval;
	}
	
	public static boolean verifyBeginLaborActivities(AssemblyLine assemblyLine) {
		LocalTime lastEnd = getLastEnd(assemblyLine);
		
		return (Step.BEGIN_LABORAL_TIME.isBefore(lastEnd) || Step.BEGIN_LABORAL_TIME.equals(lastEnd)) && Step.END_LABORAL_TIME.isAfter(lastEnd)  ;
	}

	public static LocalTime getLastEnd(AssemblyLine assemblyLine) {
		return assemblyLine.getSteps().get(assemblyLine.getSteps().size() -1).getEnd();
	}
}
