package entity.humanoid;

import controller.NPCController;
import core.Direction;
import core.Vector2D;
import entity.GameObject;
import entity.MovingEntity;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import gfx.SpriteSet;

public class Bubble extends MovingEntity {
    private boolean halted;

    public Bubble(NPCController npcController, SpriteLibrary spriteLibrary) {
        super(npcController, spriteLibrary);

        this.animationManager = new AnimationManager(new SpriteSet(spriteLibrary.getImage("bubble")), false);
    }

    @Override
    protected void handleCollision(GameObject other) {
    }

    @Override
    protected void handleMotion() {
        if (!halted) {
            motion.add(new Vector2D(0, -0.5));
        }

        halted = false;
        direction = Direction.S;
    }

    @Override
    protected String decideAnimation() {
        return "defualt";
    }

    public void halt() {
        halted = true;
    }

    public void insert(GameObject gameObject) {
        position = gameObject.getPosition();
        renderOffset = gameObject.getRenderOffset();
        collisionBoxOffset = renderOffset;
        gameObject.parent(this);
    }
}
