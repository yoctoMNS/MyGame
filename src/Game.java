import java.util.ArrayList;
import java.util.List;

public class Game {
    private Display display;
    private List<GameObject> gameObjects;
    private Input input;

    public Game(int width, int height) {
        this.input = new Input();
        this.display = new Display(width, height, input);
        this.gameObjects = new ArrayList<>();
        this.gameObjects.add(new Player(new PlayerController(input)));
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
