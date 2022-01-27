package controller;

import core.Position;

public class NPCController implements Controller {
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    @Override
    public boolean isRequestiongUp() {
        return up;
    }

    @Override
    public boolean isRequestiongDown() {
        return down;
    }

    @Override
    public boolean isRequestiongLeft() {
        return left;
    }

    @Override
    public boolean isRequestiongRight() {
        return right;
    }

    public void moveToTarget(Position target, Position current) {
        double deltaX = target.x - current.x;
        double deltaY = target.y - current.y;

        up    = deltaY < 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
        down  = deltaY > 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
        left  = deltaX < 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
        right = deltaX > 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
    }

    public void stop() {
        up = down = left = right = false;
    }
}
