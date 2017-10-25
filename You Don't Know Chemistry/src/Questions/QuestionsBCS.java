package Questions;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class QuestionsBCS {
	private File questionfile;
	private int numQuestions;
	private final int numQParts = 2;
	private String[][] questionsList;
	public int nextQuestion;

	
	public QuestionsBCS(String file_path, int numQuestions) {
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
	
	public int getRandQuestion() {
		Random random = new Random();
		nextQuestion = random.nextInt(34);
		return nextQuestion;
	}
	
	public int getAnswer(int numquestion) {
		return Integer.parseInt(this.questionsList[numquestion][1]);
	}
}
