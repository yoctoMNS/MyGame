import java.util.ArrayList;
import java.util.List;

public class Game {
    private Display display;
    private List<GameObject> gameObjects;

    public Game(int width, int height) {
        this.display = new Display(width, height);
        this.gameObjects = new ArrayList<>();
        this.gameObjects.add(new Square());
    }

    public void update() {
        gameObjects.forEach(gameObject -> gameObject.update());
    }

    public void render() {
        display.render(this);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
