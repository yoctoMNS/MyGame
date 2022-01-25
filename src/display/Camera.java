package display;

import core.Position;
import core.Size;
import entity.GameObject;
import game.state.State;

import java.util.Optional;

public class Camera {
    private Position position;
    private Size windowSize;

    private Optional<GameObject> objectWithFocus;

    public Camera(Size windowSize) {
        this.position = new Position(0, 0);
        this.windowSize = windowSize;
    }

    public void focusOn(GameObject object) {
        this.objectWithFocus = Optional.of(object);
    }

    public void update(State state) {
        if (objectWithFocus.isPresent()) {
            Position objectPosition = objectWithFocus.get().getPosition();

            this.position.x = objectPosition.x - windowSize.w / 2;
            this.position.y = objectPosition.y - windowSize.h / 2;

            clampWithinBounds(state);
        }
    }

    private void clampWithinBounds(State state) {
        if (position.x < 0) {
            position.x = 0;
        }

        if (position.y < 0) {
            position.y = 0;
        }

        if (position.x + windowSize.w > state.getGameMap().getWidth()) {
            position.x = state.getGameMap().getWidth() - windowSize.w;
        }

        if (position.y + windowSize.h > state.getGameMap().getHeight()) {
            position.y = state.getGameMap().getHeight() - windowSize.h;
        }
    }

    public Position getPosition() {
        return position;
    }
}
