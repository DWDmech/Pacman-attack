package my.game.sounds;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class SoundLoader {
	
	private static SoundLoader instance = null;
	private File soundFile = null;
	private BufferedInputStream fin = null;
	private Player player = null;
	
	protected SoundLoader(){
	}
	
	public static SoundLoader getInstance(){
		if (instance == null){
			instance = new SoundLoader();
		}
		return instance;
	}
	
	public void loadSound(){
		soundFile = new File("src\\my\\game\\sounds\\nyan.mp3");
		try {
			fin = new BufferedInputStream(new FileInputStream(soundFile));
			player = new Player(fin);
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		}
	}
	
	public void startSound(){
		try {
			player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
}
