package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import game.state.State;

import java.awt.Image;

public abstract class GameObject {
    protected Position position;
    protected Size size;

    public GameObject() {
        this.position = new Position(50, 50);
        this.size = new Size(50, 50);
    }

    public GameObject(Position position, Size size) {
        this.position = position;
        this.size = size;
    }

    public abstract void update(State state);

    public abstract Image getSprite();

    public abstract CollisionBox getCollisionBox();

    public abstract boolean collidesWith(GameObject other);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Size getSize() {
        return size;
    }
}
