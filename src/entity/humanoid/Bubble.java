package entity.humanoid;

import controller.NPCController;
import entity.GameObject;
import entity.MovingEntity;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import gfx.SpriteSet;

public class Bubble extends MovingEntity {
    private NPCController controller;

    public Bubble(NPCController npcController, SpriteLibrary spriteLibrary) {
        super(npcController, spriteLibrary);

        this.controller = npcController;
        this.animationManager = new AnimationManager(new SpriteSet(spriteLibrary.getImage("bubble")), false);
    }

    @Override
    protected void handleCollision(GameObject other) {

    }

    @Override
    protected void handleMotion() {
        motion.update(controller);
    }

    @Override
    protected String decideAnimation() {
        return "defualt";
    }

    public void insert(GameObject gameObject) {
        position = gameObject.getPosition();
        renderOffset = gameObject.getRenderOffset();
        gameObject.parent(this);
    }
}
