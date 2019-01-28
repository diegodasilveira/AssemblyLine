package com.silveira.assembly.vo;


import java.util.ArrayList;
import java.util.List;

public class StepTestData {

	public static List<Step> getStepsToOriginalTest() {
		ArrayList<Step> steps = new ArrayList<>();
		
		steps.add(Step.builder().title("Cutting of steel sheets 60min").durationMinutes(60).build());
		steps.add(Step.builder().title("Austenpera (Heat treatment) 30min").durationMinutes(30).build());
		steps.add(Step.builder().title("Tempering sub-zero (Heat treatment) 45min").durationMinutes(45).build());
		steps.add(Step.builder().title("Safety sensor assembly 60min").durationMinutes(60).build());
		steps.add(Step.builder().title("Pieces washing 45min").durationMinutes(45).build());
		steps.add(Step.builder().title("Axis calibration 30min").durationMinutes(30).build());
		steps.add(Step.builder().title("Steel bearing assembly 45min").durationMinutes(45).build());
		steps.add(Step.builder().title("Assembly line cooling - maintenance").durationMinutes(5).build());
		steps.add(Step.builder().title("Nitriding process 45min").durationMinutes(45).build());
		steps.add(Step.builder().title("Injection subsystem assembly 60min").durationMinutes(60).build());
		steps.add(Step.builder().title("Compliance check 30min").durationMinutes(30).build());
		steps.add(Step.builder().title("Navigation subsystem assembly 60min").durationMinutes(60).build());
		steps.add(Step.builder().title("Torque converter subsystem calibration 60min").durationMinutes(60).build());
		steps.add(Step.builder().title("Left stabilizer bar alignment 30min").durationMinutes(30).build());
		steps.add(Step.builder().title("Setup of lock and control device 45min").durationMinutes(45).build());
		steps.add(Step.builder().title("Right stabilizer bar alignment 30min").durationMinutes(30).build());
		steps.add(Step.builder().title("Seal installation 45min").durationMinutes(45).build());
		steps.add(Step.builder().title("Application of decals 30min").durationMinutes(30).build());
		steps.add(Step.builder().title("Monitoring subsystem assembly 30min").durationMinutes(30).build());
		
		return steps;
	}

	public static List<Step> getStepsUnordered() {
		ArrayList<Step> steps = new ArrayList<>();
		
		steps.add(Step.builder().title("One 5min").durationMinutes(5).build());
		steps.add(Step.builder().title("Two 5min").durationMinutes(5).build());
		steps.add(Step.builder().title("Three 175min").durationMinutes(175).build());
		steps.add(Step.builder().title("Four 175min").durationMinutes(175).build());
		steps.add(Step.builder().title("Five 10min").durationMinutes(10).build());
		
		return steps;
	}
	
	public static List<Step> getSmallsSteps() {
		ArrayList<Step> steps = new ArrayList<>();
		
		steps.add(Step.builder().title("A 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("B 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("C 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("D 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("E 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("F 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("G 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("H 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("I 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("J 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("K 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("L 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("M 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("N 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("O 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("P 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("Q 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("R 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("S 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("T 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("U 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("V 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("X 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("Z 15min").durationMinutes(15).build());
		
		steps.add(Step.builder().title("AA 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AB 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AC 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AD 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AE 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AF 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AG 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AH 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AI 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AJ 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AK 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AL 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AM 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AN 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AO 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AP 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AQ 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AR 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AS 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AT 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AU 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AV 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AX 15min").durationMinutes(15).build());
		steps.add(Step.builder().title("AZ 15min").durationMinutes(15).build());
		
		return steps;
	}

}
