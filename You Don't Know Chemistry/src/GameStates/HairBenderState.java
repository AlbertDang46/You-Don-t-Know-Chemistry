package GameStates;

import java.awt.Color;
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

public class HairBenderState extends GameStates {

	private int currentplayer;
	
	private int currentanswer1;
	private int currentanswer2;
	
	private long timepassed;
	private long starttime;
	private long endtime;
	
	private long qtimepassed;
	private long qstarttime;
	private long qendtime;
	
	private Sound sound;
	private Clip bgm;
	private BufferedImage[] hbimages;
	
	
	public HairBenderState(GameStatesManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		sound = new Sound();
		
		bgm = sound.loadClip("/Sounds/BackgroundMusic/HairBenderBGM.wav");
		
		try {
			hbimages = new BufferedImage[24];
			for(int i = 0; i < hbimages.length; i++) {
				hbimages[i] = ImageIO.read(new File("Resources/Animations/HairBender/hb" + (i+1) + ".png"));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
			
		
		switch(GameInfo.getQuestionsHB().getCategory()) {
			case 1:
				currentanswer1 = GameInfo.getQuestionsHB().getRandAnswer(1);
				currentanswer2 = GameInfo.getQuestionsHB().getRandAnswer(1);
			break;
			case 2:
				currentanswer1 = GameInfo.getQuestionsHB().getRandAnswer(2);
				currentanswer2 = GameInfo.getQuestionsHB().getRandAnswer(2);
			break;
		}
		
		starttime = System.nanoTime();
		qstarttime = System.nanoTime();
		
		firstaction();
	}
	
	@Override
	public void firstaction() {
		sound.loopClip(bgm);
		
	}


	@Override
	public void update() {
		handleInput();
		
		qendtime = System.nanoTime();
		qtimepassed += (qendtime-qstarttime);
		
		if(qtimepassed >= 3000000000l) {
			switch(GameInfo.getQuestionsHB().getCategory()) {
				case 1:
					currentanswer1 = GameInfo.getQuestionsHB().getRandAnswer(1);
					currentanswer2 = GameInfo.getQuestionsHB().getRandAnswer(1);
				break;
				case 2:
					currentanswer1 = GameInfo.getQuestionsHB().getRandAnswer(2);
					currentanswer2 = GameInfo.getQuestionsHB().getRandAnswer(2);
				break;
			}
		}
		else {
			starttime = System.nanoTime();
		}
		
		endtime = System.nanoTime();
		timepassed += (endtime-starttime);
		if(timepassed >= 30000000000l) {
			sound.stopClip(bgm);
			gsm.setState(GameStatesManager.SELECTCATEGORY);
		}
		else {
			starttime = System.nanoTime();
		}
	}

	@Override
	public void drawToImage(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, GameLoop.width, GameLoop.height);
		
		g.drawImage(hbimages[currentanswer1], GameLoop.width/4, GameLoop.height/2, GameLoop.width/4, GameLoop.height/4, null);
		g.drawImage(hbimages[currentanswer2], GameLoop.width/2, GameLoop.height/2, GameLoop.width/4, GameLoop.height/4, null);
		
		g.setColor(Color.white);
		switch(GameInfo.getPlayers()) {
			case 1:
				g.drawString("Player 1: " + GameInfo.getMoney(1), GameLoop.width/2, GameLoop.height*7/8);
			break;
			case 2:
				g.drawString("Player 1: " + GameInfo.getMoney(1), GameLoop.width/3, GameLoop.height*7/8);
				g.drawString("Player 2: " + GameInfo.getMoney(2), GameLoop.width*2/3, GameLoop.height*7/8);
			break;
			case 3:
				g.drawString("Player 1: " + GameInfo.getMoney(1), GameLoop.width/4, GameLoop.height*7/8);
				g.drawString("Player 2: " + GameInfo.getMoney(2), GameLoop.width*2/4, GameLoop.height*7/8);
				g.drawString("Player 3: " + GameInfo.getMoney(3), GameLoop.width*3/4, GameLoop.height*7/8);
			break;
		}
	}

	@Override
	public void handleInput() {
		switch(GameInfo.getPlayers()) {
			case 1:
				if(Keys.isPressed(Keys.B)) {
					currentplayer = 1;
				}
			break;
			case 2:
				if(Keys.isPressed(Keys.Q)) {
					currentplayer = 1;
				}
				else if(Keys.isPressed(Keys.P)) {
					currentplayer = 2;
				}
			break;
			case 3:
				if(Keys.isPressed(Keys.Q)) {
					currentplayer = 1;
				}
				else if(Keys.isPressed(Keys.B)) {
					currentplayer = 2;
				}
				else if(Keys.isPressed(Keys.P)) {
					currentplayer = 3;
				}
			break;
		}
		
		if(currentplayer != 0) {
			if(GameInfo.getQuestionsHB().getAnswer(currentanswer1) == GameInfo.getQuestionsHB().getAnswer(currentanswer2)) {
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
			}
			currentplayer = 0;
			switch(GameInfo.getQuestionsHB().getCategory()) {
				case 1:
					currentanswer1 = GameInfo.getQuestionsHB().getRandAnswer(1);
					currentanswer2 = GameInfo.getQuestionsHB().getRandAnswer(1);
				break;
				case 2:
					currentanswer1 = GameInfo.getQuestionsHB().getRandAnswer(2);
					currentanswer2 = GameInfo.getQuestionsHB().getRandAnswer(2);
				break;
			}
		}
	}
}
