package core;

public class Vector2D {
    public double x;
    public double y;

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
}