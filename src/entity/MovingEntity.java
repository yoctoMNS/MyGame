package entity;

import controller.Controller;
import core.Movement;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.Image;

public abstract class MovingEntity extends GameObject {
    private Controller controller;
    private Movement movement;
    private AnimationManager animationManager;

    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
        super();

        this.controller = controller;
        this.movement = new Movement(2);
        this.animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
    }

    @Override
    public void update() {
        movement.update(controller);
        position.apply(movement);
        animationManager.update();
    }

    @Override
    public Image getSprite() {
        return animationManager.getSprite();
    }
}
