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

public class BackstageState extends GameStates {
	
	private Sound sound;
	private Clip howmanyplayers;
	private Clip[] playerclips;
	
	private BufferedImage playerselect;
	private BufferedImage[] ps;
	
	private int playersselected;
	
	
	public BackstageState(GameStatesManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		sound = new Sound();
		
		howmanyplayers = sound.loadClip("/Sounds/Backstage/howmanyplayers.wav");
		
		playerclips = new Clip[4];
		for(int i = 0; i < playerclips.length; i++) 
			playerclips[i] = sound.loadClip("/Sounds/Backstage/" + (i+1) + "player.wav");
		
		try {
			playerselect = ImageIO.read(new File("Resources/Animations/Backstage/player select screen.jpg"));
			ps = new BufferedImage[3];
			for(int i = 0; i < 3; i++)
				ps[i] = ImageIO.read(new File("Resources/Animations/Backstage/p" + (i+1) + " select.jpg"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	
		firstaction();
	}
	
	@Override
	public void firstaction() {
		sound.playClip(howmanyplayers);		
	}

	@Override
	public void update() {
		handleInput();
		
		switch(playersselected) {
			case 1:
				if(!playerclips[0].isRunning()) {
					gsm.setState(GameStatesManager.BUTTONS);
				}
			break;
			case 2:
				if(!playerclips[1].isRunning()) {
					gsm.setState(GameStatesManager.BUTTONS);
				}
			break;
			case 3:
				if(!playerclips[2].isRunning()) {
					gsm.setState(GameStatesManager.BUTTONS);
				}
			break;
		}
	}

	@Override
	public void drawToImage(Graphics2D g) {
			g.setColor(Color.black);
			g.fillRect(0, 0, GameLoop.width, GameLoop.height);	
			
			if(playersselected == 0) 		
				g.drawImage(playerselect, 0, 0, GameLoop.width, GameLoop.height, null);	
			else if(playersselected == 1)
				g.drawImage(ps[0], 0, 0, GameLoop.width, GameLoop.height, null);
			else if(playersselected == 2)
				g.drawImage(ps[1], 0, 0, GameLoop.width, GameLoop.height, null);
			else if(playersselected == 3)
				g.drawImage(ps[2], 0, 0, GameLoop.width, GameLoop.height, null);
		}

	@Override
	public void handleInput() {
		if(Keys.isPressed(Keys.ONE)) {
			sound.playClip(playerclips[0]);		
			GameInfo.setPlayers(1);
			playersselected = 1;		
		}
		else if(Keys.isPressed(Keys.TWO)) {
			sound.playClip(playerclips[1]);
			GameInfo.setPlayers(2);
			playersselected = 2;
		}
		else if(Keys.isPressed(Keys.THREE)) {
			sound.playClip(playerclips[2]);
			GameInfo.setPlayers(3);
			playersselected = 3;
		}
		else if(Keys.isPressed(Keys.FOUR)) {
			sound.playClip(playerclips[3]);
		}
	}
}
