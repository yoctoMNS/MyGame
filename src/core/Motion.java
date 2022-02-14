package core;

import controller.EntityController;

public class Motion {
    private Vector2D vector;
    private double speed;

    public Motion(double speed) {
        this.speed = speed;
        this.vector = new Vector2D(0, 0);
    }

    public void update(EntityController entityController) {
        int deltaX = 0, deltaY = 0;

        if (entityController.isRequestiongUp()) {
            deltaY--;
        }
        if (entityController.isRequestiongDown()) {
            deltaY++;
        }
        if (entityController.isRequestiongLeft()) {
            deltaX--;
        }
        if (entityController.isRequestiongRight()) {
            deltaX++;
        }

        vector = new Vector2D(deltaX, deltaY);
        vector.normalize();
        vector.multiply(speed);
    }

    public Vector2D getVector() {
        return vector;
    }

    public boolean isMoving() {
        return vector.length() > 0;
    }

    public void multiply(double multiplier) {
        vector.multiply(multiplier);
    }

    public void stop(boolean stopX, boolean stopY) {
        vector = new Vector2D(
                stopX ? 0 : vector.x,
                stopY ? 0 : vector.y
        );
    }

    public Vector2D getDirection() {
        Vector2D direction = Vector2D.copyOf(vector);
        direction.normalize();

        return direction;
    }
}
