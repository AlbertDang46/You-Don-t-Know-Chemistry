package Main;


import javax.swing.JFrame;


public class Window 
{
	public static GameLoop gameloop = new GameLoop();

	
	public static void main(String[] args) 
	{		
		JFrame window = new JFrame("You Don't Know Chemsitry");
		
		window.setUndecorated(true);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setContentPane(gameloop);   
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
