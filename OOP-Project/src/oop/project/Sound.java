package oop.project;//this is main sound class

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30]; //use to store file path for sound files
    
    public Sound() { //this is method for sound effect
        
        soundURL[0] = getClass().getResource("/sound/theme.wav");
        soundURL[1] = getClass().getResource("/sound/pickup_key.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock_door.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
    }
    
    public void setFile (int i) { //this is file path for the audio
        
        try {
            
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]); //this is format to open audio file
            clip = AudioSystem.getClip();
            clip.open(ais); 
            
        }catch(Exception e) {
            
        }
    }
    
    public void play () {
        
        clip.start();
    }
    
    public void loop () { //this is loop for looping sound
        
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop () { //this is for stopping sound
        
        clip.stop();
    }
}
