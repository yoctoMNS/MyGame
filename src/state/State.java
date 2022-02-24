package state;

import audio.AudioPlayer;
import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import game.Game;
import game.Time;
import game.settings.GameSettings;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;
import ui.UIContainer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class State {
    protected GameSettings gameSettings;
    protected AudioPlayer audioPlayer;
    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected List<UIContainer> uiContainers;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Time time;
    protected State nextState;
    protected Size windowSize;

    public State(Size windowSize, Input input, GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        this.audioPlayer = new AudioPlayer(gameSettings.getAudioSettings());
        this.gameObjects = new ArrayList<>();
        this.uiContainers = new ArrayList<>();
        this.spriteLibrary = new SpriteLibrary();
        this.input = input;
        this.camera = new Camera(windowSize);
        this.time = new Time();
        this.windowSize = windowSize;
    }

    public void update(Game game) {
        audioPlayer.update();
        time.update();
        sortObjectByPosition();
        updateGameObjects();
        List.copyOf(uiContainers).forEach(uiContainer -> uiContainer.update(this));
        camera.update(this);
        handleMouseInput();

        if (nextState != null) {
            game.enterState(nextState);
        }
    }

    private void handleMouseInput() {
        input.clearMouseClick();
    }

    private void updateGameObjects() {
        for (int i = 0; i < gameObjects.size(); i++ ) {
            gameObjects.get(i).update(this);
        }
    }

    private void sortObjectByPosition() {
        gameObjects.sort(Comparator.comparing(GameObject::getRenderOrder).thenComparing(gameObject -> gameObject.getPosition().getY()));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Camera getCamera() {
        return camera;
    }

    public Time getTime() {
        return time;
    }

    public Position getRandomPosition() {
        return gameMap.getRandomPosition();
    }

    public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
        return gameObjects.stream()
                .filter(other -> other.collidesWith(gameObject))
                .collect(Collectors.toList());
    }

    public List<UIContainer> getUiContainers() {
        return uiContainers;
    }

    public <T extends GameObject> List<T> getGameObjectsOfClass(Class<T> clazz) {
        return gameObjects.stream()
                .filter(clazz::isInstance)
                .map(gameObject -> (T)gameObject)
                .collect(Collectors.toList());
    }

    public SpriteLibrary getSpriteLibrary() {
        return spriteLibrary;
    }

    public void spawn(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public Input getInput() {
        return input;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }
}
