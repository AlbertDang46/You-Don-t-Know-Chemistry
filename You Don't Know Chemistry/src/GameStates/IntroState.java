package GameStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import GameHandlers.Keys;
import GameHandlers.Sound;
import Main.GameLoop;

public class IntroState extends GameStates {
	
	private Sound sound;
	private Clip[] introclips;
	private Clip selectedintro;
	private BufferedImage droz;
	

	public IntroState(GameStatesManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		sound = new Sound();
		
		introclips = new Clip[2];	
		for(int i = 0; i < introclips.length; i++)
			introclips[i] = sound.loadClip("/Sounds/Intro/intro" + (i+1) + ".wav");
		
		selectedintro = introclips[new Random().nextInt(2)];
		
		try {
			droz = ImageIO.read(new File(("Resources/Animations/Intro/dr-oz-show.jpg")));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		firstaction();
	}
	
	@Override
	public void firstaction() {
		sound.playClip(selectedintro);
		
	}


	@Override
	public void update() {
		handleInput();
		
		if(!selectedintro.isRunning()) 
			gsm.setState(GameStatesManager.SELECTCATEGORY);
	}

	@Override
	public void drawToImage(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, GameLoop.width, GameLoop.height);
		
		g.drawImage(droz, 0, 0, GameLoop.width, GameLoop.height, null);	
	}

	@Override
	public void handleInput() {
		
	}
}
