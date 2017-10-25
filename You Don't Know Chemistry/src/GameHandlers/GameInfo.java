package GameHandlers;

import Questions.QuestionsBCS;
import Questions.QuestionsFITB;
import Questions.QuestionsHB;
import Questions.QuestionsMC;
import Questions.QuestionsPW;


public class GameInfo {
	
	private static int numPlayers;
	private static int player1money, player2money, player3money;
	
	private static int currentlevel;	
	private static int nextquestion;
	
	private static QuestionsMC questionsmc;
	private static QuestionsBCS questionsbcs;
	private static QuestionsPW questionspw;
	private static QuestionsFITB questionsfitb;
	private static QuestionsHB questionshb;
	
	
	static {
		 questionsmc = new QuestionsMC("/Questions/YDKC Multiple Choice.txt", 35);
		 questionsbcs = new QuestionsBCS("/Questions/YDKC Better Call Soluble.txt", 34);
		 questionspw = new QuestionsPW("/Questions/YDKC Punderwhelmer.txt", 5);
		 questionsfitb = new QuestionsFITB("/Questions/YDKC Fill In The Blank.txt", 14);
		 questionshb = new QuestionsHB("/Questions/YDKC Hair Bender.txt", 24);
	}
	
	public static int getPlayers() {
		return numPlayers;
	}
	
	public static void setPlayers(int i) {
		numPlayers = i;
	}
	
	public static int getMoney(int player) {
		switch(player) {
			case 1:
				return player1money;
			case 2:
				return player2money;
			case 3:
				return player3money;
			default:
				return 0;
		}
	}
	
	public static void addMoney(int player, int amount) {
		switch(player) {
			case 1:
				player1money += amount;
			break;
			case 2:
				player2money += amount;
			break;
			case 3:
				player3money += amount;
			break;
		}
	}
	
	public static void subtractMoney(int player, int amount) {
		switch(player) {
			case 1:
				player1money -= amount;
			break;
			case 2:
				player2money -= amount;
			break;
			case 3:
				player3money -= amount;
			break;
		}
	}
	
	public static int getCurrentLevel() {
		return currentlevel;
	}
	
	public static void advanceCurrentLevel() {
		currentlevel++;
	}
	
	public static int getNextQuestion() {
		return nextquestion;
	}
	
	public static void setNextQuestion(int nxquestion) {
		nextquestion = nxquestion;
	}

	public static QuestionsMC getQuestions() {
		return questionsmc;
	}
	
	public static QuestionsBCS getQuestionsBCS() {
		return questionsbcs;
	}
	
	public static QuestionsPW getQuestionsPW() {
		return questionspw;
	}
	
	public static QuestionsFITB getQuestionsFITB() {
		return questionsfitb;
	}
	
	public static QuestionsHB getQuestionsHB() {
		return questionshb;
	}
}
