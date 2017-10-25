package Questions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuestionsMC {
	
	private File questionfile;
	private int numQuestions;
	private final int numQParts = 7;
	private String[][] questionsList;
	private ArrayList<Integer> randcategory;
	
	
	public QuestionsMC(String file_path, int numQuestions) {
		questionfile = new File("Resources" + file_path);
			
		this.numQuestions = numQuestions;
		
		try {
			this.questionsList = OpenQuestionFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		randcategory = new ArrayList<Integer>(35);
		for(int i = 0; i < 35; i++) 
			randcategory.add(i);
		Collections.shuffle(randcategory);
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
		
	public int getRandCategory(int category) {
		int selectedcategory = 0;
		
		switch(category) {
			case 1:
				selectedcategory = randcategory.get(0);
			break;
			case 2:
				selectedcategory = randcategory.get(1);
			break;
			case 3:
				selectedcategory = randcategory.get(2);
			break;
		}			
		return selectedcategory;
	}
	
	public void removeCategory(int category) {
		randcategory.remove(category-1);
	}
	
	public String getCategory(int numQuestion) {
		return this.questionsList[numQuestion][0];
	}
	
	public String getQuestion(int numQuestion) {
		return this.questionsList[numQuestion][1];
	}
	
	public String getA(int numQuestion) {
		return this.questionsList[numQuestion][2];
	}
	
	public String getB(int numQuestion) {
		return this.questionsList[numQuestion][3];
	}
	
	public String getC(int numQuestion) {
		return this.questionsList[numQuestion][4];
	}
	
	public String getD(int numQuestion) {
		return this.questionsList[numQuestion][5];
	}
	
	public int getAnswer(int numQuestion) {
		return Integer.parseInt(this.questionsList[numQuestion][6]);
	}
}
