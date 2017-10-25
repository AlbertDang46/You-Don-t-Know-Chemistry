package GameHandlers;

import java.awt.event.KeyEvent;

public class Keys {

	public static final int NUM_KEYS = 9;
	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];
	
	public static int ONE = 0;
	public static int TWO = 1;
	public static int THREE = 2;
	public static int FOUR = 3;
	public static int Q = 4;
	public static int B = 5;
	public static int P = 6;
	public static int ENTER = 7;
	public static int ESC = 8;

	public static void keySet(int i, boolean b) {
		if(i == KeyEvent.VK_1) keyState[ONE] = b;
		else if(i == KeyEvent.VK_2) keyState[TWO] = b;
		else if(i == KeyEvent.VK_3) keyState[THREE] = b;
		else if(i == KeyEvent.VK_4) keyState[FOUR] = b;
		else if(i == KeyEvent.VK_Q) keyState[Q] = b;
		else if(i == KeyEvent.VK_B) keyState[B] = b;
		else if(i == KeyEvent.VK_P) keyState[P] = b;
		else if(i == KeyEvent.VK_ENTER) keyState[ENTER] = b;
		else if(i == KeyEvent.VK_ESCAPE) keyState[ESC] = b;
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}
	
	public static boolean isPressed(int i) {
		return keyState[i] && !prevKeyState[i];
	}
}
