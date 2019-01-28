package com.silveira.assembly.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AssemblyLine {
	
	public static final String DEFAULT_NAME = "Line ";
	
	private String name;
	
	private List<Step> steps;

}
