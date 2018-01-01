package sound;
import java.io.BufferedInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import runner.Runner;

public class SoundPlayer {
	
	private static Clip music = null;
	
	public static void playSound(String sound, Boolean isMusic){
	
		if(isMusic == true)
			if(music!=null)
				music.stop();
		
		Clip clip = null;
		
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
        AudioInputStream inputStream = null;
		try {
			inputStream = AudioSystem.getAudioInputStream(
			  new BufferedInputStream(Runner.class.getResourceAsStream("/sounds/" + sound)));
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			clip.open(inputStream);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        clip.start();
        
        if(isMusic)
        		music = clip;
        
	}
	
	public static void stopMusic(){
		if(music!=null)
			music.stop();
		else
			System.out.println("No music playing");
	}
	

}
