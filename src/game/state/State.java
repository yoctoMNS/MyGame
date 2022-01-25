package game.state;

import core.Size;
import display.Camera;
import entity.GameObject;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class State {
    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;

    public State(Size windowSize, Input input) {
        this.gameObjects = new ArrayList<>();
        this.spriteLibrary = new SpriteLibrary();
        this.input = input;
        this.camera = new Camera(windowSize);
    }

    public void update() {
        sortObjectByPosition();
        gameObjects.forEach(gameObject -> gameObject.update());
        camera.update(this);
    }

    private void sortObjectByPosition() {
        gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getPosition().y));
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
}
