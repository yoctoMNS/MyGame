package entity;

import ai.AIManager;
import controller.EntityController;
import core.Motion;
import entity.humanoid.Humanoid;
import gfx.SpriteLibrary;
import state.State;

public class NPC extends Humanoid {
    private AIManager aiManager;

    public NPC(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
        aiManager = new AIManager();
        motion = new Motion(Math.random() + 1);
    }

    @Override
    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof Player) {
            motion.stop(willCollideX(other), willCollideY(other));
        }
    }
}
