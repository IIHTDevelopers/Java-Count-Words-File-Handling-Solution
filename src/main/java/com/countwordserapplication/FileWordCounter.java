package com.countwordserapplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class FileWordCounter {

	private static final String FILE_PATH = "./src/main/java/com/countwordserapplication/data.txt";

	public static void main(String[] args) {
		try {
			int uniqueWordCount = countUniqueWords();
			System.out.println("Unique Word Count: " + uniqueWordCount);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static int countUniqueWords() throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.print("Enter text to save in 'data.txt': ");
			String inputText = reader.readLine();
			saveInputToFile(inputText);

			File dataFile = new File(FILE_PATH);
			String fileContent = readFileContent(dataFile);
			return countUniqueWords(fileContent);
		}
	}

	public static void saveInputToFile(String inputText) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
			writer.write(inputText);
			System.out.println("Input saved to 'data.txt'");
		} catch (IOException e) {
			System.out.println("Error while saving input to file: " + e.getMessage());
		}
	}

	public static String readFileContent(File file) throws IOException {
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		}
		return content.toString();
	}

	public static int countUniqueWords(String inputText) {
		Set<String> uniqueWords = new HashSet<>();
		String[] words = inputText.split("\\W+");
		for (String word : words) {
			if (!word.isEmpty()) {
				uniqueWords.add(word.toLowerCase());
			}
		}
		return uniqueWords.size();
	}
}