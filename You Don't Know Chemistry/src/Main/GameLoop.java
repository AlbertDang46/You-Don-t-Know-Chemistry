package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import GameHandlers.Keys;
import GameStates.GameStatesManager;


public class GameLoop extends JPanel implements Runnable, KeyListener
{
	public static int width;
	public static int height;
	
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long frameTime = 1000/FPS;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private GameStatesManager gsm;	
	
	
	public GameLoop()
	{
		super();
		
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		
		if(thread == null)
		{
			thread = new Thread(this);		
			thread.start();
		}
	}
	
	private void visualize() {
		long time = 0;
		long lasttime = 0;
		
		while(true) {
			if(this.getWidth() > 1 && time > 1000000000L) {
				width = this.getWidth();
				height = this.getHeight();
				break;
			} else {
				time += System.nanoTime() - lasttime;
				lasttime = System.nanoTime();
			}
		}
	}
	
	private void init()
	{
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		gsm = new GameStatesManager();
	}
	
	@Override
	public void run()
	{
		visualize();	
		init();
		
		long start;
		long elapsed;
		long wait;
		
		while(running)
		{
			start = System.nanoTime();
			
			update();
			drawToImage();
			repaint();
			
			elapsed = System.nanoTime()-start;
			
			wait = frameTime - elapsed/1000000; 
			if(wait < 0)
				wait = 5;
			
			try 
			{
				Thread.sleep(wait);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}		
	}
	
	private void update()
	{
		gsm.update();
		Keys.update();
	}
	
	private void drawToImage()
	{
		gsm.drawToImage(g);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, width, height, null);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		Keys.keySet(key.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent key) {
		Keys.keySet(key.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent key){}
}
