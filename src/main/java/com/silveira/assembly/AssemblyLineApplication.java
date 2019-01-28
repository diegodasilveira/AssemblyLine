package com.silveira.assembly;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.silveira.assembly.processor.AssemblyLineProcessor;
import com.silveira.assembly.processor.InputFileReader;
import com.silveira.assembly.processor.OutputFileWritter;
import com.silveira.assembly.vo.AssemblyLine;
import com.silveira.assembly.vo.Step;

@SpringBootApplication
public class AssemblyLineApplication {
	
	private static final Logger log = LoggerFactory.getLogger(AssemblyLineApplication.class);
	
    @Autowired
    private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(AssemblyLineApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner run() {
		 return (args) -> {
	            try {
	            	Path inputPath = validateDir(env.getProperty("file.input.path"));
	            	Path outputPath = validateDir(env.getProperty("file.output.path"));
	            	
	            	List<Path> filesInput = Files.walk(inputPath)
	                	.filter(Files::isRegularFile)
	                	.collect(Collectors.toList());
	            	
	            	for (Path input : filesInput) {
	            		List<Step> steps = InputFileReader.readSteps(input);
	            		List<AssemblyLine> assemblyLines = new AssemblyLineProcessor().process(steps);
	            		OutputFileWritter.output(assemblyLines, outputPath, input.getFileName().toString());
					}
	            	
	            } catch (Exception e) {
	                if (e.getCause() != null) {
	                    log.error("Falha ao executar", e.getCause());
	                } else {
	                    log.error("Falha ao executar", e);
	                }
	            }
		 };
	}

	private Path validateDir(String pathConfigured) {
		Path path = Paths.get(".");
		if (!StringUtils.isEmpty(pathConfigured)) {
			if (Files.isDirectory(Paths.get(pathConfigured)) && Files.isWritable(Paths.get(pathConfigured))) {
				return Paths.get(pathConfigured);
			}
		}
		
		log.warn("Diretório configurado é inválido!");
		
		return path;
	}
}

