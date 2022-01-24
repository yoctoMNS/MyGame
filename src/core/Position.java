package core;

public class Position {
    public double x;
    public double y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int)Math.round(x);
    }

    public int getY() {
        return (int)Math.round(y);
    }

    public void apply(Movement movement) {
        Vector2D v = movement.getVector();
        x += v.x;
        y += v.y;
    }
}
