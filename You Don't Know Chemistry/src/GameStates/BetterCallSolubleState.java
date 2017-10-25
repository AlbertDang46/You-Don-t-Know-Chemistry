package GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import GameHandlers.GameInfo;
import GameHandlers.Keys;
import GameHandlers.Sound;
import Main.GameLoop;

public class BetterCallSolubleState extends GameStates {

	private int currentplayer;
	private int selectedanswer;
	private int currentquestion;
	private int numquestions;
	private boolean selected;
	
	private Sound sound;
	private Clip bgm;
	private Clip correct, incorrect, playerselected;
	private Clip bcsintro;
	private Clip nofollow;
	private BufferedImage[] bcsimages;
	private BufferedImage yes, no;
	private BufferedImage[] playerimage;
	private BufferedImage[] highlightplayer;
	
	
	public BetterCallSolubleState(GameStatesManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		sound = new Sound();
		correct = sound.loadClip("/Sounds/Effects/Correct.wav");
		incorrect = sound.loadClip("/Sounds/Effects/Incorrect.wav");
		playerselected = sound.loadClip("/Sounds/Effects/PlayerSelected.wav");
		bgm = sound.loadClip("/Sounds/BackgroundMusic/BetterCallSolubleBGM.wav");
		bcsintro = sound.loadClip("/Sounds/BetterCallSoluble/BCS Intro.wav");
		nofollow = sound.loadClip("/Sounds/BetterCallSoluble/NoFollowDirections.wav");
		
		try {
			bcsimages = new BufferedImage[34];
			for(int i = 0; i < bcsimages.length; i++) {
				bcsimages[i] = ImageIO.read(new File("Resources/Animations/BetterCallSoluble/bettercallsoluble (" + (i+1) + ").gif"));
			}
			
			yes = ImageIO.read(new File("Resources/Animations/BetterCallSoluble/yes.png"));
			no = ImageIO.read(new File("Resources/Animations/BetterCallSoluble/no.png"));
			
			playerimage = new BufferedImage[3];
			for(int i = 0; i < 3; i++)
				playerimage[i] = ImageIO.read(new File("Resources/Animations/Players/Player " + (i+1) + ".jpg"));
			highlightplayer = new BufferedImage[3];
			for(int i = 0; i <3; i++)
				highlightplayer[i] = ImageIO.read(new File("Resources/Animations/Players/highlight p" + (i+1) + ".jpg"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		currentquestion = GameInfo.getQuestionsBCS().getRandQuestion();
		
		firstaction();
	}
	
	@Override
	public void firstaction() {
		sound.loopClip(bgm);
		sound.playClip(bcsintro);
		
	}

	@Override
	public void update() {
		handleInput();
		
	}

	@Override
	public void drawToImage(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, GameLoop.width, GameLoop.height);
		
		g.drawImage(bcsimages[currentquestion], GameLoop.width/2-bcsimages[currentquestion].getWidth()/2, GameLoop.height/2-bcsimages[currentquestion].getHeight()/2, bcsimages[currentquestion].getWidth(), bcsimages[currentquestion].getHeight(), null);
		
		g.drawImage(yes, GameLoop.width/3-yes.getWidth()/2, GameLoop.height*2/3-yes.getHeight()/2, yes.getWidth(), yes.getHeight(), null);
		g.drawImage(no, GameLoop.width*2/3-no.getWidth(), GameLoop.height*2/3-no.getHeight()/2, no.getWidth(), no.getHeight(), null);
		
		g.setColor(Color.white);
		g.setFont(new Font("Ariel", Font.BOLD, 30));	
		switch(GameInfo.getPlayers()) {
			case 1:
				g.drawImage(playerimage[0], GameLoop.width/2-playerimage[0].getWidth()/2, GameLoop.height*19/24-playerimage[0].getHeight()/2, playerimage[0].getWidth(), playerimage[0].getHeight(), null);
				
				if(currentplayer == 1) 
					g.drawImage(highlightplayer[0], GameLoop.width/2-highlightplayer[0].getWidth()/2, GameLoop.height*19/24-highlightplayer[0].getHeight()/2, highlightplayer[0].getWidth(), highlightplayer[0].getHeight(), null);
				
				g.drawString("$" + GameInfo.getMoney(1), GameLoop.width/2-playerimage[0].getWidth()/2, GameLoop.height*9/10);
			break;
			case 2:
				g.drawImage(playerimage[0], GameLoop.width/3-playerimage[0].getWidth()/2, GameLoop.height*19/24-playerimage[0].getHeight()/2, playerimage[0].getWidth(), playerimage[0].getHeight(), null);
				g.drawImage(playerimage[1], GameLoop.width*2/3-playerimage[1].getWidth()/2, GameLoop.height*19/24-playerimage[1].getHeight()/2, playerimage[0].getWidth(), playerimage[0].getHeight(), null);
				
				if(currentplayer == 1) 
					g.drawImage(highlightplayer[0], GameLoop.width/3-highlightplayer[0].getWidth()/2, GameLoop.height*19/24-highlightplayer[0].getHeight()/2, highlightplayer[0].getWidth(), highlightplayer[0].getHeight(), null);
				else if(currentplayer == 2)
					g.drawImage(highlightplayer[1], GameLoop.width*2/3-highlightplayer[1].getWidth()/2, GameLoop.height*19/24-highlightplayer[1].getHeight()/2, highlightplayer[1].getWidth(), highlightplayer[1].getHeight(), null);
				
				g.drawString("$" + GameInfo.getMoney(1), GameLoop.width/3-playerimage[0].getWidth()/2, GameLoop.height*9/10);
				g.drawString("$" + GameInfo.getMoney(2), GameLoop.width*2/3-playerimage[1].getWidth()/2, GameLoop.height*9/10);
			break;
			case 3:
				g.drawImage(playerimage[0], GameLoop.width/4-playerimage[0].getWidth()/2, GameLoop.height*19/24-playerimage[0].getHeight()/2, playerimage[0].getWidth(), playerimage[0].getHeight(), null);
				g.drawImage(playerimage[1], GameLoop.width/2-playerimage[1].getWidth()/2, GameLoop.height*19/24-playerimage[1].getHeight()/2, playerimage[0].getWidth(), playerimage[0].getHeight(), null);
				g.drawImage(playerimage[2], GameLoop.width*3/4-playerimage[2].getWidth()/2, GameLoop.height*19/24-playerimage[2].getHeight()/2, playerimage[0].getWidth(), playerimage[0].getHeight(), null);
				
				if(currentplayer == 1) 
					g.drawImage(highlightplayer[0], GameLoop.width/4-highlightplayer[0].getWidth()/2, GameLoop.height*19/24-highlightplayer[0].getHeight()/2, highlightplayer[0].getWidth(), highlightplayer[0].getHeight(), null);
				else if(currentplayer == 2)
					g.drawImage(highlightplayer[1], GameLoop.width/2-highlightplayer[1].getWidth()/2, GameLoop.height*19/24-highlightplayer[1].getHeight()/2, highlightplayer[1].getWidth(), highlightplayer[1].getHeight(), null);
				else if(currentplayer == 3)
					g.drawImage(highlightplayer[2], GameLoop.width*3/4-highlightplayer[2].getWidth()/2, GameLoop.height*19/24-highlightplayer[2].getHeight()/2, highlightplayer[2].getWidth(), highlightplayer[2].getHeight(), null);
				
				g.drawString("$ " + GameInfo.getMoney(1), GameLoop.width/4-playerimage[0].getWidth()/2, GameLoop.height*9/10);
				g.drawString("$" + GameInfo.getMoney(2), GameLoop.width/2-playerimage[1].getWidth()/2, GameLoop.height*9/10);
				g.drawString("$" + GameInfo.getMoney(3), GameLoop.width*3/4-playerimage[2].getWidth()/2, GameLoop.height*9/10);
			break;
		}
	}

	@Override
	public void handleInput() {
		if(!selected) {
			switch(GameInfo.getPlayers()) {
				case 1:
					if(Keys.isPressed(Keys.B)) {
						currentplayer = 1;
						selected = true;
						sound.playClip(playerselected);
					}
				break;
				case 2:
					if(Keys.isPressed(Keys.Q)) {
						currentplayer = 1;
						selected = true;
						sound.playClip(playerselected);
					}
					else if(Keys.isPressed(Keys.P)) {
						currentplayer = 2;
						selected = true;
						sound.playClip(playerselected);
					}
				break;
				case 3:
					if(Keys.isPressed(Keys.Q)) {
						currentplayer = 1;
						selected = true;
						sound.playClip(playerselected);
					}
					else if(Keys.isPressed(Keys.B)) {
						currentplayer = 2;
						selected = true;
						sound.playClip(playerselected);
					}
					else if(Keys.isPressed(Keys.P)) {
						currentplayer = 3;
						selected = true;
						sound.playClip(playerselected);
					}
				break;
			}
		}
		
		if(currentplayer != 0) {
			if(Keys.isPressed(Keys.ONE)) {
				selectedanswer = 1;
			}
			else if(Keys.isPressed(Keys.TWO)) {
				selectedanswer = 2;
			}
			else if(Keys.isPressed(Keys.THREE)) {
				sound.playClip(nofollow);
			}
		}
		
		if(selectedanswer != 0) {
			if(selectedanswer == GameInfo.getQuestionsBCS().getAnswer(currentquestion)) {
				switch(currentplayer) {
					case 1:
						GameInfo.addMoney(1, 100);
					break;
					case 2:
						GameInfo.addMoney(2, 100);
					break;
					case 3:
						GameInfo.addMoney(3, 100);
					break;
				}
				sound.playClip(correct);
			}
			else {
				switch(currentplayer) {
					case 1:
						GameInfo.subtractMoney(1, 100);
					break;
					case 2:
						GameInfo.subtractMoney(2, 100);
					break;
					case 3:
						GameInfo.subtractMoney(3, 100);
					break;
				}
				sound.playClip(incorrect);
			}
			selectedanswer = 0;
			currentplayer = 0;
			currentquestion = GameInfo.getQuestionsBCS().getRandQuestion();
			selected = false;
			numquestions++;
			
			if(numquestions == 7) {
				sound.stopClip(bgm);
				gsm.setState(GameStatesManager.SELECTCATEGORY);
			}
		}
	}
}

