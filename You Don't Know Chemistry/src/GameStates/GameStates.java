package GameStates;

import java.awt.Graphics2D;


public abstract class GameStates
{
	protected GameStatesManager gsm;
	
	public GameStates(GameStatesManager gsm)
	{
		this.gsm = gsm;
	}
	
	public abstract void init();
	public abstract void firstaction();
	public abstract void update();
	public abstract void drawToImage(Graphics2D g);
	public abstract void handleInput();
}
