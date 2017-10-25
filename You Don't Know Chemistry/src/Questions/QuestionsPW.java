package Questions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class QuestionsPW {

	private File questionfile;
	private int numQuestions;
	private final int numQParts = 2;
	private String[][] questionsList;

	public QuestionsPW(String file_path, int numQuestions) {
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
		int nextQuestion = random.nextInt(5);
		return nextQuestion;
	}
	
	public String getAnswer(int numquestion) {		
		return this.questionsList[numquestion][1];
	}
}
