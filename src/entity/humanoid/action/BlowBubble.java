package entity.humanoid.action;

import controller.NPCController;
import entity.humanoid.Bubble;
import entity.humanoid.Humanoid;
import entity.humanoid.effect.Isolated;
import game.GameLoop;
import state.State;

public class BlowBubble extends Action {
    private int lifeSpanInUpdates;
    private Humanoid target;

    public BlowBubble(Humanoid target) {
        this.target = target;
        this.lifeSpanInUpdates = GameLoop.UPDATES_PER_SECOND;
        this.interruptable = false;
    }

    private Bubble bubble;

    @Override
    public void update(State state, Humanoid humanoid) {
        lifeSpanInUpdates--;

        if (bubble == null) {
            bubbleTarget(state);
        } else {
            bubble.halt();
        }

        if (isDone()) {
            target.setRenderOrder(6);
            bubble.setRenderOrder(6);
        }
    }

    private void bubbleTarget(State state) {
        target.perform(new Levitate());
        target.addEffect(new Isolated());

        bubble = new Bubble(new NPCController(), state.getSpriteLibrary());
        bubble.insert(target);
        state.spawn(bubble);
    }

    @Override
    public boolean isDone() {
        return lifeSpanInUpdates == 0;
    }

    @Override
    public String getAnimationName() {
        return "blow";
    }
}
