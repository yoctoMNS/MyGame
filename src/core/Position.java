package core;

public class Position {
    public static int PROXIMITY_RANGE = 5;

    public double x;
    public double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

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

    public void apply(Motion movement) {
        Vector2D v = movement.getVector();
        x += v.x;
        y += v.y;
    }

    public boolean isInRangeOf(Position position) {
        return Math.abs(x - position.x) < Position.PROXIMITY_RANGE && Math.abs(y - position.y) < Position.PROXIMITY_RANGE;
    }
}
