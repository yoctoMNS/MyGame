package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import game.state.State;

import javax.management.remote.rmi.RMIIIOPServerImpl;
import java.awt.Image;

public abstract class GameObject {
    protected Position position;
    protected Size size;
    protected GameObject parent;

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

    public boolean collidesWith(GameObject other) {
        return getCollisionBox().collidesWith(other.getCollisionBox());
    }

    public Position getPosition() {
        Position finalPosition = Position.copyOf(position);

        if (parent != null) {
            finalPosition.add(parent.position);
        }

        return finalPosition;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Size getSize() {
        return size;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }
}
