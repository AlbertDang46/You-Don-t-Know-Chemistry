package GameStates;

import java.awt.BorderLayout;
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
import GameHandlers.VideoPlayer;
import Main.GameLoop;
import Main.Window;

public class SelectCategoryState extends GameStates {

	private Sound sound;
	private Clip bgm;
	private VideoPlayer videoplayer;
	private long visualizingTime;
	private long currentTime;
	private long lastTime;
	private long startbgm;
	
	private int randquestion1, randquestion2, randquestion3;
	private String category1, category2, category3;
	
	private BufferedImage[] categoryimages1, categoryimages2, categoryimages3;
	private Animation cateAnim1, cateAnim2, cateAnim3;
	

	public SelectCategoryState(GameStatesManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		sound = new Sound();
		bgm = sound.loadClip("/Sounds/BackgroundMusic/CategorySelectionBGM.wav");
		
		videoplayer = new VideoPlayer();
		Window.gameloop.add(videoplayer, BorderLayout.CENTER);
		Window.gameloop.revalidate();
			
		randquestion1 = GameInfo.getQuestions().getRandCategory(1);
		randquestion2 = GameInfo.getQuestions().getRandCategory(2);
		randquestion3 = GameInfo.getQuestions().getRandCategory(3);	
		category1 = GameInfo.getQuestions().getCategory(randquestion1);
		category2 = GameInfo.getQuestions().getCategory(randquestion2);
		category3 = GameInfo.getQuestions().getCategory(randquestion3);
		
		try {
			categoryimages1 = new BufferedImage[5];
			for(int i = 0; i < 5; i++)
				categoryimages1[i] = ImageIO.read(new File("Resources/Animations/Categories/category (" + (randquestion1+1) + ")/c (" + (i+1) + ").gif"));
			categoryimages2 = new BufferedImage[5];
			for(int i = 0; i < 5; i++)
				categoryimages2[i] = ImageIO.read(new File("Resources/Animations/Categories/category (" + (randquestion2+1) + ")/c (" + (i+1) + ").gif"));
			categoryimages3 = new BufferedImage[5];
			for(int i = 0; i < 5; i++)
				categoryimages3[i] = ImageIO.read(new File("Resources/Animations/Categories/category (" + (randquestion3+1) + ")/c (" + (i+1) + ").gif"));
			
			cateAnim1 = new Animation(categoryimages1, categoryimages1[0].getWidth(), categoryimages1[0].getHeight(), 5, 40, true, GameLoop.width/2-categoryimages1[0].getWidth()/2, GameLoop.height/4-categoryimages1[0].getHeight()/2, 0);
			cateAnim2 = new Animation(categoryimages2, categoryimages2[0].getWidth(), categoryimages2[0].getHeight(), 5, 40, true, GameLoop.width/2-categoryimages2[0].getWidth()/2, GameLoop.height/2-categoryimages2[0].getHeight()/2, 0);
			cateAnim3 = new Animation(categoryimages3, categoryimages3[0].getWidth(), categoryimages3[0].getHeight(), 5, 40, true, GameLoop.width/2-categoryimages3[0].getWidth()/2, GameLoop.height*3/4-categoryimages3[0].getHeight()/2, 0);
		} catch(IOException e) {
			e.printStackTrace();
		}

		firstaction();
	}
	
	@Override
	public void firstaction() {	
		GameInfo.advanceCurrentLevel();
		
		switch(GameInfo.getCurrentLevel()) {
			case 1:
				videoplayer.playVideo("/Videos/Categories/1.mp4");
			break;
			case 2:
				videoplayer.playVideo("/Videos/Categories/2.mp4");
			break;
			case 3:
				videoplayer.playVideo("/Videos/Categories/3.mp4");
			break;
			case 4:
				videoplayer.playVideo("/Videos/Categories/4.mp4");
			break;
			case 5:
				videoplayer.playVideo("/Videos/Categories/5.mp4");
			break;
			case 6:
				videoplayer.playVideo("/Videos/Categories/6.mp4");
			break;
			case 8:
				videoplayer.playVideo("/Videos/Categories/7.mp4");
			break;
			case 9:
				videoplayer.playVideo("/Videos/Categories/8.mp4");
			break;
			case 10:
				videoplayer.playVideo("/Videos/Categories/9.mp4");
			break;
			case 11:
				videoplayer.playVideo("/Videos/Categories/10.mp4");
			break;
		}
		
		lastTime = System.nanoTime();
	}

	@Override
	public void update() {
		handleInput();
		
		if(startbgm == 1) {
			sound.loopClip(bgm);
		}
		
		if(visualizingTime >= 1000000000) {
			if(!videoplayer.videoPlaying()) {
				Window.gameloop.removeAll();
				Window.gameloop.revalidate();
				startbgm += 1;
			}
		}
		else {
			currentTime = System.nanoTime();
			visualizingTime += (currentTime - lastTime);
			lastTime = System.nanoTime();
		}	
		
		if(GameInfo.getCurrentLevel() == 7) {
			if(visualizingTime >= 1000000000) {
				if(!videoplayer.videoPlaying()) {
					Window.gameloop.removeAll();
					Window.gameloop.revalidate();
					sound.stopClip(bgm);
					gsm.setState(GameStatesManager.VIDEOINTERMISSION);
				}
			}
			else {
				currentTime = System.nanoTime();
				visualizingTime += (currentTime - lastTime);
				lastTime = System.nanoTime();
			}		
		}
		
		if(GameInfo.getCurrentLevel() == 8) {
			if(visualizingTime >= 1000000000) {
				if(!videoplayer.videoPlaying()) {
					Window.gameloop.removeAll();
					Window.gameloop.revalidate();
					sound.stopClip(bgm);
					gsm.setState(GameStatesManager.BETTERCALLSOLUBLE);
				}
			}
			else {
				currentTime = System.nanoTime();
				visualizingTime += (currentTime - lastTime);
				lastTime = System.nanoTime();
			}		
		}
		
		if(GameInfo.getCurrentLevel() == 12) {
			if(visualizingTime >= 1000000000) {
				if(!videoplayer.videoPlaying()) {
					Window.gameloop.removeAll();
					Window.gameloop.revalidate();
					sound.stopClip(bgm);
					gsm.setState(GameStatesManager.PUNDERWHELMER);
				}
			}
			else {
				currentTime = System.nanoTime();
				visualizingTime += (currentTime - lastTime);
				lastTime = System.nanoTime();
			}			
		}
		
		if(GameInfo.getCurrentLevel() >= 13 && GameInfo.getCurrentLevel() <= 15) {
			if(visualizingTime >= 1000000000) {
				if(!videoplayer.videoPlaying()) {
					Window.gameloop.removeAll();
					Window.gameloop.revalidate();
					sound.stopClip(bgm);
					gsm.setState(GameStatesManager.FILLINTHEBLANK);
				}
			}
			else {
				currentTime = System.nanoTime();
				visualizingTime += (currentTime - lastTime);
				lastTime = System.nanoTime();
			}			
		}
		if(GameInfo.getCurrentLevel() == 16) {
			if(visualizingTime >= 1000000000) {
				if(!videoplayer.videoPlaying()) {
					Window.gameloop.removeAll();
					Window.gameloop.revalidate();
					sound.stopClip(bgm);
					gsm.setState(GameStatesManager.DISPLAYSCORES);
				}
			}
			else {
				currentTime = System.nanoTime();
				visualizingTime += (currentTime - lastTime);
				lastTime = System.nanoTime();
			}			
		}
		
		if(GameInfo.getCurrentLevel() == 17) {
			if(visualizingTime >= 1000000000) {
				if(!videoplayer.videoPlaying()) {
					Window.gameloop.removeAll();
					Window.gameloop.revalidate();
					sound.stopClip(bgm);
					gsm.setState(GameStatesManager.CONCLUSION);
				}
			}
			else {
				currentTime = System.nanoTime();
				visualizingTime += (currentTime - lastTime);
				lastTime = System.nanoTime();
			}			
		}
	}

	@Override
	public void drawToImage(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, GameLoop.width, GameLoop.height);
		
		if(GameInfo.getCurrentLevel() < 7 || (GameInfo.getCurrentLevel() >= 9 && GameInfo.getCurrentLevel() <= 11)) {
			cateAnim1.Draw(g);
			cateAnim2.Draw(g);
			cateAnim3.Draw(g);
		}
		
	/*	if(GameInfo.getCurrentLevel() == 16) {
			g.setColor(Color.white);
			g.drawString(GameInfo.getQuestionsHB().getCategoryName(1), GameLoop.width/2, GameLoop.height/3);
			g.drawString(GameInfo.getQuestionsHB().getCategoryName(2), GameLoop.width/2, GameLoop.height*2/3);
		} */	
	}

	@Override
	public void handleInput() {
		if(GameInfo.getCurrentLevel() < 7 || (GameInfo.getCurrentLevel() >= 9 && GameInfo.getCurrentLevel() <= 11)) {
			if(Keys.isPressed(Keys.ONE)) {
				GameInfo.setNextQuestion(randquestion1);
				GameInfo.getQuestions().removeCategory(1);
				sound.stopClip(bgm);
				gsm.setState(GameStatesManager.MULTIPLECHOICE);
			}
			else if(Keys.isPressed(Keys.TWO)) {
				GameInfo.setNextQuestion(randquestion2);
				GameInfo.getQuestions().removeCategory(2);
				sound.stopClip(bgm);
				gsm.setState(GameStatesManager.MULTIPLECHOICE);
			}
			else if(Keys.isPressed(Keys.THREE)) {
				GameInfo.setNextQuestion(randquestion3);
				GameInfo.getQuestions().removeCategory(3);
				sound.stopClip(bgm);
				gsm.setState(GameStatesManager.MULTIPLECHOICE);
			}
		}
		
	/*	else if(GameInfo.getCurrentLevel() == 16) {
			if(Keys.isPressed(Keys.ONE)) {
				GameInfo.getQuestionsHB().setCategory(1);
				gsm.setState(GameStatesManager.HAIRBENDER);
			}
			else if(Keys.isPressed(Keys.TWO)) {
				GameInfo.getQuestionsHB().setCategory(2);
				gsm.setState(GameStatesManager.HAIRBENDER);
			}		
		} */
	}
}
