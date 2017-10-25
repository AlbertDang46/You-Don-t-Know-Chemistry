package GameHandlers;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound {
	
	public Clip loadClip(String filename) {
		Clip clip = null;
		
		try {
			//AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource(filename));
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("Resources" + filename));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return clip;
	}
	
	public void playClip(Clip clip) {
		stopClip(clip);
		clip.start();
	}
	
	public void loopClip(Clip clip) {
		stopClip(clip);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stopClip(Clip clip) {
		if(clip.isRunning()) {
			clip.stop();
		}
		clip.setFramePosition(0);
	}
}
