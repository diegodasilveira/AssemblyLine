package com.silveira.assembly.processor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.silveira.assembly.vo.AssemblyLine;
import com.silveira.assembly.vo.Step;

public class OutputFileWritter {
	
	private static final String ERROR_MESSAGE = "Erro ao escrever arquivo de saída.";

	private static final Logger log = LoggerFactory.getLogger(OutputFileWritter.class);

	private static final String STEP_PATTERN = "{0} - {1}";
	
	private static final DateTimeFormatter TIME_PATTERN = DateTimeFormatter.ofPattern("HH:mm");
	
	public static void output(List<AssemblyLine> assemblyLines, Path path, String fileNameOutput) {
		List<String> lines = new ArrayList<>();
		
		assemblyLines.stream().forEach(assemblyLine -> { 
			lines.add(assemblyLine.getName());
			assemblyLine.getSteps().stream().forEach(step -> lines.add(getLineText(step)));
		});
		
		Path fileToCreate = path.resolve(fileNameOutput);
		try {
			Files.deleteIfExists(fileToCreate);
			Path newFilePath = Files.createFile(fileToCreate);
			Files.write(newFilePath, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			log.error(ERROR_MESSAGE, e);
		}
	}

	private static String getLineText(Step step) {
		return MessageFormat.format(STEP_PATTERN, step.getBegin().format(TIME_PATTERN), step.getTitle());
	}
}
