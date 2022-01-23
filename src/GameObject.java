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

    public abstract void update();

    public abstract Image getSprite();

    public Position getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }
}
