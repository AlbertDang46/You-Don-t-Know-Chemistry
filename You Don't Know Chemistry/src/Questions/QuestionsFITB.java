package Questions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuestionsFITB {
	
	private File questionfile;
	private int numQuestions;
	private final int numQParts = 2;
	private String[][] questionsList;
	private ArrayList<Integer> randquestion;

	public QuestionsFITB(String file_path, int numQuestions) {
		questionfile = new File("Resources" + file_path);
		
		this.numQuestions = numQuestions;
		
		try {
			this.questionsList = OpenQuestionFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		randquestion = new ArrayList<Integer>(14);
		for(int i = 0; i < 14; i++) 
			randquestion.add(i);
		Collections.shuffle(randquestion);
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
		return randquestion.get(0);
	}
	
	public void removeQuestion() {
		randquestion.remove(0);
	}
	
	public String getAnswer(int numquestion) {		
		return this.questionsList[numquestion][1];
	}
}
