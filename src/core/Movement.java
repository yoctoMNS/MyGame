package core;

import controller.Controller;

public class Movement {
    private Vector2D vector;
    private double speed;

    public Movement(double speed) {
        this.speed = speed;
        this.vector = new Vector2D(0, 0);
    }

    public void update(Controller controller) {
        int deltaX = 0, deltaY = 0;

        if (controller.isRequestiongUp()) {
            deltaY--;
        }
        if (controller.isRequestiongDown()) {
            deltaY++;
        }
        if (controller.isRequestiongLeft()) {
            deltaX--;
        }
        if (controller.isRequestiongRight()) {
            deltaX++;
        }

        vector = new Vector2D(deltaX, deltaY);
        vector.normalize();
        vector.multiply(speed);
        System.out.println(vector.length());
    }

    public Vector2D getVector() {
        return vector;
    }
}
