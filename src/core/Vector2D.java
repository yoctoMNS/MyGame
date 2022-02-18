package core;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(Vector2D vector) {
        this.x = vector.x;;
        this.y = vector.y;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        double length = length();
        x = x == 0 ? 0 : x / length;
        y = y == 0 ? 0 : y / length;
    }

    public static Vector2D copyOf(Vector2D vector) {
        return new Vector2D(vector);
    }

    public static Vector2D directionBetweenPositions(Position start, Position end) {
        Vector2D direction = new Vector2D(start.x - end.x, start.y - end.y);
        direction.normalize();

        return direction;
    }

    public static double dotProduct(Vector2D v1, Vector2D v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public void multiply(double speed) {
        x *= speed;
        y *= speed;
    }

    public void add(Vector2D vector) {
        x += vector.x;
        y += vector.y;
    }
}
