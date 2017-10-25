package GameStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import GameHandlers.Animation;
import GameHandlers.GameInfo;
import GameHandlers.Keys;
import GameHandlers.Sound;
import Main.GameLoop;

public class ButtonsState extends GameStates {

	private Sound sound;
	private Clip[] buttonclips;
	
	private BufferedImage[] bbtnAnim, pbtnAnim, qbtnAnim;
	private Animation bAnim1, bAnim3, pAnim2, qAnim2, pAnim3, qAnim3;
	
	
	public ButtonsState(GameStatesManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		sound = new Sound();
		
		buttonclips = new Clip[3];
		for(int i = 0; i < buttonclips.length; i++) 
			buttonclips[i] = sound.loadClip("/Sounds/Buttons/" + (i+1) + "playerbutton.wav");
		
		try {
			bbtnAnim = new BufferedImage[5];
			for(int i = 0; i < 5; i++)
				bbtnAnim[i] = ImageIO.read(new File("Resources/Animations/Buttons/B/b (" + (i+1) + ").gif"));
			pbtnAnim = new BufferedImage[5];
			for(int i = 0; i < 5; i++)
				pbtnAnim[i] = ImageIO.read(new File("Resources/Animations/Buttons/P/p (" + (i+1) + ").gif"));
			qbtnAnim = new BufferedImage[5];
			for(int i = 0; i < 5; i++)
				qbtnAnim[i] = ImageIO.read(new File("Resources/Animations/Buttons/Q/q (" + (i+1) + ").gif"));
			
			bAnim1 = new Animation(bbtnAnim, bbtnAnim[0].getWidth(), bbtnAnim[0].getHeight(), 5, 20, true, GameLoop.width/2-bbtnAnim[0].getWidth()/2, GameLoop.height/2-bbtnAnim[0].getHeight()/2, 0);
			pAnim2 = new Animation(pbtnAnim, pbtnAnim[0].getWidth(), pbtnAnim[0].getHeight(), 5, 20, true, GameLoop.width*2/3-pbtnAnim[0].getWidth()/2, GameLoop.height/2-pbtnAnim[0].getHeight()/2, 0);
			qAnim2 = new Animation(qbtnAnim, qbtnAnim[0].getWidth(), qbtnAnim[0].getHeight(), 5, 20, true, GameLoop.width/3-qbtnAnim[0].getWidth()/2, GameLoop.height/2-qbtnAnim[0].getHeight()/2, 0);
			bAnim3 = new Animation(bbtnAnim, bbtnAnim[0].getWidth(), bbtnAnim[0].getHeight(), 5, 20, true, GameLoop.width/2-bbtnAnim[0].getWidth()/2, GameLoop.height/2-bbtnAnim[0].getHeight()/2, 0);
			pAnim3 = new Animation(pbtnAnim, pbtnAnim[0].getWidth(), pbtnAnim[0].getHeight(), 5, 20, true, GameLoop.width*3/4-pbtnAnim[0].getWidth()/2, GameLoop.height/2-pbtnAnim[0].getHeight()/2, 0);
			qAnim3 = new Animation(qbtnAnim, qbtnAnim[0].getWidth(), qbtnAnim[0].getHeight(), 5, 20, true, GameLoop.width/4-qbtnAnim[0].getWidth()/2, GameLoop.height/2-qbtnAnim[0].getHeight()/2, 0);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		firstaction();
	}
	
	@Override
	public void firstaction() {
		switch(GameInfo.getPlayers()) {
			case 1:
				sound.playClip(buttonclips[0]);
			break;
			case 2:
				sound.playClip(buttonclips[1]);
			break;
			case 3:
				sound.playClip(buttonclips[2]);
			break;
		} 
	}

	@Override
	public void update() {
		handleInput();
		
		switch(GameInfo.getPlayers()) {
			case 1:
				if(!buttonclips[0].isRunning())
					gsm.setState(GameStatesManager.INTRO);
			break;
			case 2:
				if(!buttonclips[1].isRunning())
					gsm.setState(GameStatesManager.INTRO);
			break;
			case 3:
				if(!buttonclips[2].isRunning())
					gsm.setState(GameStatesManager.INTRO);
			break;
		}
	}

	@Override
	public void drawToImage(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, GameLoop.width, GameLoop.height);
		
		switch(GameInfo.getPlayers()) {
			case 1:
				bAnim1.Draw(g);
			break;
			case 2:
				qAnim2.Draw(g);
				pAnim2.Draw(g);
			break;
			case 3:		
				qAnim3.Draw(g);
				bAnim3.Draw(g);
				pAnim3.Draw(g);
			break;
		}
	}

	@Override
	public void handleInput() {
	
	}
}
