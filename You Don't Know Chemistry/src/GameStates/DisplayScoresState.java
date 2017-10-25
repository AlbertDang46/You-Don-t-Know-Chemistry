package GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameHandlers.GameInfo;
import GameHandlers.Keys;
import Main.GameLoop;

public class DisplayScoresState extends GameStates {
	
	private BufferedImage[] playerimage;
	private BufferedImage[] highlightplayer;
	

	public DisplayScoresState(GameStatesManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {

		try {
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
		
	}


	@Override
	public void update() {
		handleInput();
		
	}

	@Override
	public void drawToImage(Graphics2D g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, GameLoop.width, GameLoop.height);
		
		g.setColor(Color.white);
		g.setFont(new Font("Osaka", Font.BOLD, 100));
		g.drawString("YOUR SCORES", GameLoop.width/4, GameLoop.height/4);
		
		g.setColor(Color.white);
		g.setFont(new Font("Ariel", Font.BOLD, 30));	
		switch(GameInfo.getPlayers()) {
			case 1:
				g.drawImage(playerimage[0], GameLoop.width/2-playerimage[0].getWidth()/2, GameLoop.height/2-playerimage[0].getHeight()/2, playerimage[0].getWidth(), playerimage[0].getHeight(), null);
				
				g.drawString("$" + GameInfo.getMoney(1), GameLoop.width/2-playerimage[0].getWidth()/2, GameLoop.height*2/3);
			break;
			case 2:
				g.drawImage(playerimage[0], GameLoop.width/3-playerimage[0].getWidth()/2, GameLoop.height/2-playerimage[0].getHeight()/2, playerimage[0].getWidth(), playerimage[0].getHeight(), null);
				g.drawImage(playerimage[1], GameLoop.width*2/3-playerimage[1].getWidth()/2, GameLoop.height/2-playerimage[1].getHeight()/2, playerimage[0].getWidth(), playerimage[0].getHeight(), null);
				
				g.drawString("$" + GameInfo.getMoney(1), GameLoop.width/3-playerimage[0].getWidth()/2, GameLoop.height*2/3);
				g.drawString("$" + GameInfo.getMoney(2), GameLoop.width*2/3-playerimage[1].getWidth()/2, GameLoop.height*2/3);
			break;
			case 3:
				g.drawImage(playerimage[0], GameLoop.width/4-playerimage[0].getWidth()/2, GameLoop.height/2-playerimage[0].getHeight()/2, playerimage[0].getWidth(), playerimage[0].getHeight(), null);
				g.drawImage(playerimage[1], GameLoop.width/2-playerimage[1].getWidth()/2, GameLoop.height/2-playerimage[1].getHeight()/2, playerimage[0].getWidth(), playerimage[0].getHeight(), null);
				g.drawImage(playerimage[2], GameLoop.width*3/4-playerimage[2].getWidth()/2, GameLoop.height/2-playerimage[2].getHeight()/2, playerimage[0].getWidth(), playerimage[0].getHeight(), null);
				
				g.drawString("$ " + GameInfo.getMoney(1), GameLoop.width/4-playerimage[0].getWidth()/2, GameLoop.height*2/3);
				g.drawString("$" + GameInfo.getMoney(2), GameLoop.width/2-playerimage[1].getWidth()/2, GameLoop.height*2/3);
				g.drawString("$" + GameInfo.getMoney(3), GameLoop.width*3/4-playerimage[2].getWidth()/2, GameLoop.height*2/3);
			break;
		}
	}

	@Override
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) {
			gsm.setState(GameStatesManager.SELECTCATEGORY);
		}	
	}
}
