package GameHandlers;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.NativeLibrary;


public class VideoPlayer extends JPanel{
	
	private EmbeddedMediaPlayerComponent mediaPlayerComponent;
		
	public VideoPlayer() {
		this.setLayout(new BorderLayout());
		
		new NativeDiscovery().discover();
		
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		
		this.add(mediaPlayerComponent, BorderLayout.CENTER);		
	}
	
	public void playVideo(String video_path) {
		mediaPlayerComponent.getMediaPlayer().playMedia("Resources" + video_path);
	}
	
	public boolean videoPlaying() {
		return mediaPlayerComponent.getMediaPlayer().isPlaying();
	}
}
