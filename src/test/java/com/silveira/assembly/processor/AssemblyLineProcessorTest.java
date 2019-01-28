package com.silveira.assembly.processor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.silveira.assembly.vo.AssemblyLine;
import com.silveira.assembly.vo.StepTestData;

public class AssemblyLineProcessorTest {

	@Test
	public void testOriginalInput() throws IOException {

		AssemblyLineProcessor processor = new AssemblyLineProcessor();
		List<AssemblyLine> result = processor.process(StepTestData.getStepsToOriginalTest());
		
		Path tmpDir = Files.createTempDirectory("neogrid");		
		OutputFileWritter.output(result, tmpDir, "original_test.txt");
		
		String expected = new String(Files.readAllBytes(Paths.get("src/test/output/original_test.txt"))); 
		String actual = new String(Files.readAllBytes(tmpDir.resolve("original_test.txt")));
		
		Assert.assertEquals(expected, actual);		
	}
	
	@Test
	public void testInverseOrder() throws IOException {

		AssemblyLineProcessor processor = new AssemblyLineProcessor();
		List<AssemblyLine> result = processor.process(StepTestData.getStepsUnordered());
		
		Path tmpDir = Files.createTempDirectory("neogrid");		
		OutputFileWritter.output(result, tmpDir, "inverse_order.txt");
		
		String expected = new String(Files.readAllBytes(Paths.get("src/test/output/inverse_order.txt"))); 
		String actual = new String(Files.readAllBytes(tmpDir.resolve("inverse_order.txt")));
		
		Assert.assertEquals(expected, actual);		
	}
	
	@Test
	public void testSmallSteps() throws IOException {
		AssemblyLineProcessor processor = new AssemblyLineProcessor();
		List<AssemblyLine> result = processor.process(StepTestData.getSmallsSteps());
		
		Path tmpDir = Files.createTempDirectory("neogrid");		
		OutputFileWritter.output(result, tmpDir, "small_steps.txt");
		
		String expected = new String(Files.readAllBytes(Paths.get("src/test/output/small_steps.txt"))); 
		String actual = new String(Files.readAllBytes(tmpDir.resolve("small_steps.txt")));
		
		Assert.assertEquals(expected, actual);	
	}
}
