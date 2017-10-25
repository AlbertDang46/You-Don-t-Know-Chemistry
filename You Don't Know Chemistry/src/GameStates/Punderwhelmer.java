package GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JTextField;

import GameHandlers.GameInfo;
import GameHandlers.Keys;
import GameHandlers.Sound;
import Main.GameLoop;
import Main.Window;

public class Punderwhelmer extends GameStates {

	private int currentplayer;	
	private JTextField pwtf;
	private BufferedImage[] pwimages;
	private int firstquestion;
	
	private Sound sound;
	private Clip correct, incorrect, playerselected;
	private BufferedImage[] playerimage;
	private BufferedImage[] highlightplayer;
	
	public Punderwhelmer(GameStatesManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		sound = new Sound();
		
		correct = sound.loadClip("/Sounds/Effects/Correct.wav");
		incorrect = sound.loadClip("/Sounds/Effects/Incorrect.wav");
		playerselected = sound.loadClip("/Sounds/Effects/PlayerSelected.wav");
		
		try {
			pwimages = new BufferedImage[5];
			for(int i = 0; i < pwimages.length; i++) {
				pwimages[i] = ImageIO.read(new File("Resources/Animations/Punderwhelmer/punder" + (i+1) + ".gif"));
			}
			
			playerimage = new BufferedImage[3];
			for(int i = 0; i < 3; i++)
				playerimage[i] = ImageIO.read(new File("Resources/Animations/Players/Player " + (i+1) + ".jpg"));
			highlightplayer = new BufferedImage[3];
			for(int i = 0; i <3; i++)
				highlightplayer[i] = ImageIO.read(new File("Resources/Animations/Players/highlight p" + (i+1) + ".jpg"));
		} catch(IOException e) {
			e.printStackTrace();
		}

		firstquestion = GameInfo.getQuestionsPW().getRandQuestion();
		
		pwtf = new JTextField();
		pwtf.setBounds(GameLoop.width/2-100, GameLoop.height/2-25, 200, 50);
		pwtf.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Window.gameloop.add(pwtf);

		firstaction();
	}
	
	@Override
	public void firstaction() {
	
	}

	@Override
	public void update() {
		handleInput();
	}

	@Override
	public void drawToImage(Graphics2D g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, GameLoop.width, GameLoop.height);
		
		g.drawImage(pwimages[firstquestion], GameLoop.width/4, GameLoop.height/6, GameLoop.width/2, GameLoop.height/4, null);
		
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
		switch(GameInfo.getPlayers()) {
			case 1:
				if(Keys.isPressed(Keys.B)) {
					currentplayer = 1;
					sound.playClip(playerselected);
				}
			break;
			case 2:
				if(Keys.isPressed(Keys.Q)) {
					currentplayer = 1;
					sound.playClip(playerselected);
				}
				else if(Keys.isPressed(Keys.P)) {
					currentplayer = 2;
					sound.playClip(playerselected);
				}
			break;
			case 3:
				if(Keys.isPressed(Keys.Q)) {
					currentplayer = 1;
					sound.playClip(playerselected);
				}
				else if(Keys.isPressed(Keys.B)) {
					currentplayer = 2;
					sound.playClip(playerselected);
				}
				else if(Keys.isPressed(Keys.P)) {
					currentplayer = 3;
					sound.playClip(playerselected);
				}
			break;
		}
		
		if(currentplayer != 0) {
			if(Keys.isPressed(Keys.ENTER)) {
				if(pwtf.getText().equalsIgnoreCase(GameInfo.getQuestionsPW().getAnswer(firstquestion))) {
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
				Window.gameloop.removeAll();
				Window.gameloop.revalidate();
				gsm.setState(GameStatesManager.SELECTCATEGORY);
			}
		}
	}
}
