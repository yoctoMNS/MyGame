package game.settings;

public class GameSettings {
    private boolean debugMode;
    private double gameSpeedMultiplier;
    private AudioSettings audioSettings;

    public GameSettings(boolean debugMode) {
        this.debugMode = debugMode;
        this.gameSpeedMultiplier = 1;
        this.audioSettings = new AudioSettings();
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

    public AudioSettings getAudioSettings() {
        return audioSettings;
    }
}
