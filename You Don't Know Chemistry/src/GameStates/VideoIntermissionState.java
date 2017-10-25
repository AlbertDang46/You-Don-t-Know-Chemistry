package GameStates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import GameHandlers.VideoPlayer;
import Main.GameLoop;
import Main.Window;

public class VideoIntermissionState extends GameStates {
	
	private VideoPlayer videoplayer;
	private long visualizingTime;
	private long currentTime;
	private long lastTime;
	
	
	public VideoIntermissionState(GameStatesManager gsm) {
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
		int selectvideo = new Random().nextInt(5);
		
		switch(selectvideo) {
			case 0:
				videoplayer.playVideo("/Videos/Clips/Dry Ice Infomercial.mp4");
			break;
			case 1:
				videoplayer.playVideo("/Videos/Clips/Mr Dr Reverend Uncle Anuj K Patel MD the IV.mp4");
			break;
			case 2:
				videoplayer.playVideo("/Videos/Clips/Baking Soda.mp4");
			break;
			case 3:
				videoplayer.playVideo("/Videos/Clips/RedOx.mp4");
			break;
			case 4:
				videoplayer.playVideo("/Videos/Clips/Kc Step.mp4");
			break;
		}
		
		lastTime = System.nanoTime();
	}

	@Override
	public void update() {
		handleInput();		
		if(visualizingTime >= 1000000000) {
			if(!videoplayer.videoPlaying()) {
				Window.gameloop.removeAll();
				Window.gameloop.revalidate();
				gsm.setState(GameStatesManager.SELECTCATEGORY);
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
		g.fillRect(0, 0, GameLoop.width, GameLoop.height);
		
	}

	@Override
	public void handleInput() {

	}
}
