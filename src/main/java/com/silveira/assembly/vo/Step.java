package com.silveira.assembly.vo;

import java.time.LocalTime;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Step {
	
	public static LocalTime BEGIN_MORNING  = LocalTime.of(9, 0);
	public static LocalTime END_MORNING  = LocalTime.of(12, 0);

	public static LocalTime BEGIN_LUNCH  = LocalTime.of(12, 0);
	public static LocalTime END_LUNCH  = LocalTime.of(13, 0);
	
	public static LocalTime BEGIN_AFTERNOON  = LocalTime.of(13, 0);
	
	public static LocalTime BEGIN_LABORAL_TIME  = LocalTime.of(16, 0);
	public static LocalTime END_LABORAL_TIME  = LocalTime.of(17, 0);
	
	@EqualsAndHashCode.Include
	private String title;
	
	@EqualsAndHashCode.Include
	private int durationMinutes;
	
	private LocalTime begin;
	
	private LocalTime end;

	public static Step getStepLunchDefault() {
		return Step.builder().title("Lunch").durationMinutes(60).begin(BEGIN_LUNCH).end(END_LUNCH).build();
	}
	
	public static Step getStepLaboralDefault(LocalTime begin) {
		return Step.builder().title("Labor Gymnastics Activities").durationMinutes(60).begin(begin).end(END_LABORAL_TIME).build();
	}

}
