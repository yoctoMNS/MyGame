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

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    public static Position copyOf(Position position) {
        return new Position(position);
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

    public void applyX(Motion motion) {
        x += motion.getVector().x;
    }

    public void applyY(Motion motion) {
        y += motion.getVector().y;
    }

    public void add(Position position) {
        x += position.x;
        y += position.y;
    }
}
