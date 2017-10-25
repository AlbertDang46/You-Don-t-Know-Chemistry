package GameStates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import GameHandlers.Keys;
import GameHandlers.VideoPlayer;
import Main.GameLoop;
import Main.Window;

public class ConclusionState extends GameStates {

	private VideoPlayer videoplayer;
	private long visualizingTime;
	private long currentTime;
	private long lastTime;
	
	
	public ConclusionState(GameStatesManager gsm) {
		super(gsm);
		init();	
	}

	@Override
	public void init() {
		videoplayer = new VideoPlayer();
		Window.gameloop.add(videoplayer, BorderLayout.CENTER);
		Window.gameloop.revalidate();
		
		firstaction();
	}
	
	@Override
	public void firstaction() {
		videoplayer.playVideo("/Videos/Categories/Game Over.mp4");
		
		lastTime = System.nanoTime();
	}

	@Override
	public void update() {
		handleInput();
		
		if(visualizingTime >= 1000000000) {
			if(!videoplayer.videoPlaying()) {
				Window.gameloop.removeAll();
				Window.gameloop.revalidate();
			}
		}
		else {
			currentTime = System.nanoTime();
			visualizingTime += (currentTime - lastTime);
			lastTime = System.nanoTime();
		}
	}

	@Override
	public void drawToImage(Graphics2D g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, GameLoop.width, GameLoop.height);
		
		g.setColor(Color.white);
		g.setFont(new Font("Osaka", Font.BOLD, 100));
		g.drawString("PRESS ESCAPE TO EXIT", GameLoop.width/4, GameLoop.height/2);
	}

	@Override
	public void handleInput() {
		if(Keys.isPressed(Keys.ESC)) {
			System.exit(0);
		}	
	}
}
