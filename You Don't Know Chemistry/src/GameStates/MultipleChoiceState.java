package GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import GameHandlers.Animation;
import GameHandlers.GameInfo;
import GameHandlers.Keys;
import GameHandlers.Sound;
import Main.GameLoop;

public class MultipleChoiceState extends GameStates {

	private int currentplayer;
	private int selectedanswer;
	private boolean selected;
	private boolean answered;
	private int wait;
	
	private Sound sound;
	private Clip mcclip;
	private Clip[] correctclips;
	private Clip[] errorclips;
	private Clip selectedcorrect, selectederror;
	private Clip correct, incorrect, playerselected;

	private BufferedImage[] mcAnim;
	private int nummcAnimImages;
	private Animation questionanim;
	
	private BufferedImage[] playerimage;
	private BufferedImage[] highlightplayer;
	
	
	public MultipleChoiceState(GameStatesManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		sound = new Sound();
		
		mcclip = sound.loadClip("/Sounds/MultipleChoice/mc" + (GameInfo.getNextQuestion()+1) + ".wav");
		
		correctclips = new Clip[7];
		for(int i = 0; i < correctclips.length; i++) 
			correctclips[i] = sound.loadClip("/Sounds/Correct/ca" + (i+1) + ".wav");
		selectedcorrect = correctclips[new Random().nextInt(7)];
			
		errorclips = new Clip[6];
		for(int i = 0; i < errorclips.length; i++) 
			errorclips[i] = sound.loadClip("/Sounds/Errors/error" + (i+1) + ".wav");
		selectederror = errorclips[new Random().nextInt(6)];
		
		correct = sound.loadClip("/Sounds/Effects/Correct.wav");
		incorrect = sound.loadClip("/Sounds/Effects/Incorrect.wav");
		playerselected = sound.loadClip("/Sounds/Effects/PlayerSelected.wav");
		
		
		try {
			File thumbs = new File("Resources/Animations/MultipleChoice/Q" + (GameInfo.getNextQuestion()+1) + "/Thumbs.db");
			if(!thumbs.exists()) 
				nummcAnimImages = new File("Resources/Animations/MultipleChoice/Q" + (GameInfo.getNextQuestion()+1)).list().length;	
			else 
				nummcAnimImages = new File("Resources/Animations/MultipleChoice/Q" + (GameInfo.getNextQuestion()+1)).list().length - 1;				
			
			mcAnim = new BufferedImage[nummcAnimImages];
			for(int i = 0; i < nummcAnimImages; i++) {
				mcAnim[i] = ImageIO.read(new File("Resources/Animations/MultipleChoice/Q" + (GameInfo.getNextQuestion()+1) + "/mc" + String.format("%03d", (i+1)) + ".jpg"));
			}
			
			questionanim = new Animation(mcAnim, mcAnim[0].getWidth(), mcAnim[0].getHeight(), nummcAnimImages, 40, 
										 false, GameLoop.width/2-mcAnim[0].getWidth()/2, 0, 0);
			
			playerimage = new BufferedImage[3];
			for(int i = 0; i < 3; i++)
				playerimage[i] = ImageIO.read(new File("Resources/Animations/Players/Player " + (i+1) + ".jpg"));
			highlightplayer = new BufferedImage[3];
			for(int i = 0; i <3; i++)
				highlightplayer[i] = ImageIO.read(new File("Resources/Animations/Players/highlight p" + (i+1) + ".jpg"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}	
		
		firstaction();
	}
	
	@Override
	public void firstaction() {
		sound.playClip(mcclip);
	}


	@Override
	public void update() {
		handleInput();
		
		if(answered)
			wait++;
		
		if(answered && wait >= 60 && (!selectedcorrect.isRunning() && !selectederror.isRunning())) {
			gsm.setState(GameStatesManager.SELECTCATEGORY);
		}
	}

	@Override
	public void drawToImage(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, GameLoop.width, GameLoop.height);
		
		questionanim.Draw(g);
		
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
				selectedanswer = 3;
			}
			else if(Keys.isPressed(Keys.FOUR)) {
				selectedanswer = 4;
			}
		}
		
		if(selectedanswer != 0) {
			sound.stopClip(mcclip);
			if(selectedanswer == GameInfo.getQuestions().getAnswer(GameInfo.getNextQuestion())) {
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
				sound.playClip(selectedcorrect);
				answered = true;
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
				sound.playClip(selectederror);
				answered = true;
			}
			selectedanswer = 0;
			currentplayer = 0;
		}
	}
}
