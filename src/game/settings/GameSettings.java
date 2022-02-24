package game.settings;

public class GameSettings {
    private boolean debugMode;
    private double gameSpeedMultiplier;
    private float musicVolume;
    private float soundVolume;

    public GameSettings(boolean debugMode) {
        this.debugMode = debugMode;
        this.gameSpeedMultiplier = 1;
        this.musicVolume = 0;
        this.soundVolume = 0;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void toggleDebugMode() {
        debugMode = !debugMode;
    }

    public void increaseGameSpeed() {
        gameSpeedMultiplier += 0.25;
    }

    public void decreaseGameSpeed() {
        gameSpeedMultiplier -= 0.25;
    }

    public double getGameSpeedMultiplier() {
        return gameSpeedMultiplier;
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
