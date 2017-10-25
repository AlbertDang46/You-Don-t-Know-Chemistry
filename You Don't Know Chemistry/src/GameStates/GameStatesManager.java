package GameStates;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GameLoop;


public class GameStatesManager 
{
	private GameStates[] gameStates;
	private int currentState;
	
	public static final int NUMGAMESTATES = 12;
	public static final int BACKSTAGE = 0;
	public static final int BUTTONS = 1;
	public static final int INTRO = 2;
	public static final int SELECTCATEGORY = 3;
	public static final int MULTIPLECHOICE = 4;
	public static final int VIDEOINTERMISSION = 5;
	public static final int BETTERCALLSOLUBLE = 6;
	public static final int PUNDERWHELMER = 7;
	public static final int FILLINTHEBLANK = 8;
	public static final int HAIRBENDER = 9;
	public static final int DISPLAYSCORES = 10;
	public static final int CONCLUSION = 11;

	
	public GameStatesManager()
	{
		gameStates = new GameStates[NUMGAMESTATES];
		
		currentState = BACKSTAGE;
		loadState(currentState);
	}
	
	private void loadState(int state)
	{
		if(state == BACKSTAGE)
			gameStates[state] = new BackstageState(this);
		else if(state == BUTTONS)
			gameStates[state] = new ButtonsState(this);
		else if(state == INTRO)
			gameStates[state] = new IntroState(this);
		else if(state == SELECTCATEGORY)
			gameStates[state] = new SelectCategoryState(this);
		else if(state == MULTIPLECHOICE)
			gameStates[state] = new MultipleChoiceState(this);
		else if(state == VIDEOINTERMISSION)
			gameStates[state] = new VideoIntermissionState(this);
		else if(state == BETTERCALLSOLUBLE)
			gameStates[state] = new BetterCallSolubleState(this);
		else if(state == PUNDERWHELMER)
			gameStates[state] = new Punderwhelmer(this);
		else if(state == FILLINTHEBLANK)
			gameStates[state] = new FillInTheBlankState(this);
		else if(state == HAIRBENDER)
			gameStates[state] = new HairBenderState(this);
		else if(state == DISPLAYSCORES)
			gameStates[state] = new DisplayScoresState(this);
		else if(state == CONCLUSION)
			gameStates[state] = new ConclusionState(this);
	}
	
	private void unloadState(int state)
	{
		gameStates[state] = null;
	}
	
	public void setState(int state)
	{
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}	
	
	public void update()
	{
		if(gameStates[currentState] != null)
		{
			gameStates[currentState].update();
		}
	}
	
	public void drawToImage(Graphics2D g)
	{
		if(gameStates[currentState] != null)
		{
			gameStates[currentState].drawToImage(g);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0,  GameLoop.width, GameLoop.height);
		}
	}
}
