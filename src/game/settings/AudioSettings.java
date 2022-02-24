package game.settings;

public class AudioSettings {
    private float musicVolume;
    private float soundVolume;

    public AudioSettings() {
        this.musicVolume = 1;
        this.soundVolume = 1;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }
}

