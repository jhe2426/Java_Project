package bubble.game.music;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BGM {
	static Clip clip ;
	public BGM() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/bgm.wav"));
			clip = AudioSystem.getClip();
			clip.open(ais);
			
			
			//소리 설정
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			
			//볼륨 조절
			gainControl.setValue(-30.0f);
			
			clip.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void PlayerDie() {
		clip.close();
		try {
			AudioInputStream die = AudioSystem.getAudioInputStream(new File("sounds/gameover.wav"));
			Clip clip2 = AudioSystem.getClip();
			clip2.open(die);
			
			//소리 설정
			FloatControl gainControl = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
			
			//볼륨 조절
			gainControl.setValue(-30.0f);
			clip2.start();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
