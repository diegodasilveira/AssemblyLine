package com.silveira.assembly.util;

import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.silveira.assembly.processor.InputFileReader;
import com.silveira.assembly.vo.Step;
import com.silveira.assembly.vo.StepTestData;

public class InputFileUtilTest {

	@Test
	public void testReadFile() {
		List<Step> expected = StepTestData.getStepsToOriginalTest();
		List<Step> actual = InputFileReader.readSteps(Paths.get("src/test/input/original_test.txt"));
		
		Assert.assertEquals(expected, actual);
	}

}
