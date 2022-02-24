package audio;

import game.settings.AudioSettings;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer {
    private AudioSettings audioSettings;
    private List<AudioClip> audioClips;

    public AudioPlayer(AudioSettings audioSettings) {
        this.audioSettings = audioSettings;
        this.audioClips = new ArrayList<>();
    }

    public void playMusic(String fileName) {
        final Clip clip = getClip(fileName);
        final MusicClip musicClip = new MusicClip(clip);
        musicClip.setVolume(audioSettings);
        audioClips.add(new MusicClip(clip));
    }

    public void playSound(String fileName) {
        final Clip clip = getClip(fileName);
        final SoundClip soundClip = new SoundClip(clip);
        soundClip.setVolume(audioSettings);
        audioClips.add(soundClip);
    }

    public void update() {
        audioClips.forEach(audioClip -> audioClip.update(audioSettings));

        List.copyOf(audioClips).forEach(audioClip -> {
            if (audioClip.hasFinishedPlaying()) {
                audioClip.cleanUp();
                audioClips.remove(audioClip);
            }
        });
    }

    private Clip getClip(String fileName) {
        final URL soundFile = AudioPlayer.class.getResource("/sounds/" + fileName);

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setMicrosecondPosition(0);

            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        return null;
    }
}
