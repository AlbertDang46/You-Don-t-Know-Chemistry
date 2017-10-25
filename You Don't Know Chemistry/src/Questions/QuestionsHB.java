package Questions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class QuestionsHB {
	
	private File questionfile;
	private int numQuestions;
	private final int numQParts = 3;
	private String[][] questionsList;
	private int category;
	
	public QuestionsHB(String file_path, int numQuestions) {
		questionfile = new File("Resources" + file_path);
		
		this.numQuestions = numQuestions;
		
		try {
			this.questionsList = OpenQuestionFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String[][] OpenQuestionFile() throws IOException {
		Scanner scanner = new Scanner(this.questionfile);
		
		String[][] textData = new String[this.numQuestions][this.numQParts];
		
		for(int i = 0; i < this.numQuestions; i++) {
			for(int j = 0; j < this.numQParts; j++) {
				textData[i][j] = scanner.nextLine();
			}
		}
		
		scanner.close();
		return textData;
	}
	
	public String getCategoryName(int category) {
		String selectcategory = null;
		switch(category) {
			case 1:
				selectcategory = "In other words...";
			break;
			case 2:
				selectcategory = "I Ship That";
			break;
		}
		return selectcategory;
	}
	
	public int getCategory() {
		return this.category;
	}
	
	public void setCategory(int category) {
		this.category = category;
	}
	
	public int getRandAnswer(int hbcategory) {
		int randcategory = 0;
		switch(hbcategory) {
			case 1:
				randcategory = new Random().nextInt(11);
			break;
			case 2:
				randcategory = 23-new Random().nextInt(11);
			break;
		}
		return randcategory;
	}
	
	public int getAnswer(int numquestion) {
		return Integer.parseInt(questionsList[numquestion][2]);
	}
}
