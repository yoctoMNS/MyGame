package entity;

import controller.EntityController;
import core.CollisionBox;
import core.Direction;
import core.Motion;
import core.Position;
import core.Size;
import core.Vector2D;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class MovingEntity extends GameObject {
    protected EntityController entityController;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;
    protected Size collisionBoxSize;
    protected Vector2D directionVector;

    public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary) {
        super();

        this.entityController = entityController;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.animationManager = new AnimationManager(spriteLibrary.getSpriteSet("matt"));
        this.directionVector = new Vector2D(0, 0);
        this.collisionBoxSize = new Size(size.w, size.h);
    }

    @Override
    public void update(State state) {
        motion.update(entityController);
        handleMotion();
        animationManager.update(direction);
        handleCollisions(state);
        manageDirection();
        animationManager.playAnimation(decideAnimation());
        position.apply(motion);
    }

    private void handleCollisions(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);
    }

    protected abstract void handleCollision(GameObject other);

    protected abstract void handleMotion();

    protected abstract String decideAnimation();

    private void manageDirection() {
        if (motion.isMoving()) {
            direction = Direction.fromMotion(motion);
            directionVector = motion.getDirection();
        }
    }

    @Override
    public Image getSprite() {
        return animationManager.getSprite();
    }

    public EntityController getController() {
        return entityController;
    }

    public void multiplySpeed(double multiplier) {
        motion.multiply(multiplier);
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position positionWithMotion = Position.copyOf(getPosition());
        positionWithMotion.apply(motion);
        positionWithMotion.subtract(collisionBoxOffset);

        return new CollisionBox(
                new Rectangle(
                        positionWithMotion.getX(),
                        positionWithMotion.getY(),
                        collisionBoxSize.w,
                        collisionBoxSize.h
                )
        );
    }

    public boolean willCollideX(GameObject other) {
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithXApplied = Position.copyOf(position);
        positionWithXApplied.applyX(motion);
        positionWithXApplied.subtract(collisionBoxOffset);

        return CollisionBox.of(positionWithXApplied, collisionBoxSize).collidesWith(otherBox);
    }

    public boolean willCollideY(GameObject other) {
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithYApplied = Position.copyOf(position);
        positionWithYApplied.applyY(motion);
        positionWithYApplied.subtract(collisionBoxOffset);

        return CollisionBox.of(positionWithYApplied, collisionBoxSize).collidesWith(otherBox);
    }

    public boolean isFacing(Position other) {
        Vector2D direction = Vector2D.directionBetweenPositions(other, position);
        double dotProduct = Vector2D.dotProduct(direction, directionVector);

        return dotProduct > 0;
    }
}
