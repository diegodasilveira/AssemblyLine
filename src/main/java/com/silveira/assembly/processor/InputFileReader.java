package com.silveira.assembly.processor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.silveira.assembly.vo.Step;

public class InputFileReader {
	
	private static final Logger log = LoggerFactory.getLogger(InputFileReader.class);

	private static final String REGEX_ONLY_NUMBERS = "\\D+";
	private static final String MAINTENANCE_KEY = "maintenance";

	public static List<Step> readSteps(Path pathInputFile) {
		List<String> lines = readLines(pathInputFile);
		List<Step> steps = new ArrayList<>();
		
		lines.stream()
				.filter(line-> isValidLine(line))
				.forEach(line -> {
					
			steps.add(Step.builder()
						.title(line)
						.durationMinutes(getDuration(line))
					.build());
		});
		
		return steps;
	}

	private static boolean isValidLine(String line) {		
		return StringUtils.hasText(line) && getDuration(line) > 0;
	}

	private static List<String> readLines(Path pathInputFile) {
		List<String> lines = null;
		
		try {
			lines = Files.readAllLines(pathInputFile, StandardCharsets.UTF_8);
		} catch (IOException e) {
			log.error("Erro ao ler o arquivo de entrada!", e);
		}
		
		return lines;
	}

	private static int getDuration(String line) {
		int duration = 0;
		
		if (line.contains(MAINTENANCE_KEY)) {
			duration = 5;
		} else {
			String numbers = line.replaceAll(REGEX_ONLY_NUMBERS, "");
			duration = (StringUtils.hasText(numbers)) ? Integer.parseInt(numbers) : duration;
		}
		
		return duration;
	}

}
