package com.assessment.countwordsapplication.functional;

import static com.assessment.countwordsapplication.testutils.TestUtils.businessTestFile;
import static com.assessment.countwordsapplication.testutils.TestUtils.currentTest;
import static com.assessment.countwordsapplication.testutils.TestUtils.testReport;
import static com.assessment.countwordsapplication.testutils.TestUtils.yakshaAssert;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import com.countwordserapplication.FileWordCounter;

@Component
public class FunctionalTests {

	private static final String TEST_FILE_PATH = "./src/main/java/com/countwordserapplication/data.txt";

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCountWords() throws IOException {
		try {
			yakshaAssert(currentTest(), FileWordCounter.countUniqueWords("this is a new this") == 4, businessTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testDataFileContent() throws IOException {
		FileWordCounter.saveInputToFile("this is a new this");
		try {
			String fileContent = FileWordCounter.readFileContent(new File(TEST_FILE_PATH));
			yakshaAssert(currentTest(), fileContent.trim().equals("this is a new this"), businessTestFile);
		} catch (IOException e) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testDataFileCreation() throws IOException {
		try {
			FileWordCounter.countUniqueWords("this is a new this");
			File dataFile = new File(TEST_FILE_PATH);
			yakshaAssert(currentTest(), dataFile.exists(), businessTestFile);
		} catch (IOException e) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

}
