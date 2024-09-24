import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.net.URL;

public class Sound {
    Clip clip;


    public void playSound(String filePath) {
        try {
            // Load the sound file as an AudioInputStream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Get a sound clip resource
            clip = AudioSystem.getClip();

            // Open the audio clip and load samples from the audio input stream
            clip.open(audioStream);

            // Play the clip
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Stop the currently playing sound
    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}


