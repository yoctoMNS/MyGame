package entity.humanoid.action;

import entity.humanoid.Humanoid;
import game.state.State;

public class Levitate extends Action {
    @Override
    public void update(State state, Humanoid humanoid) {
        this.interruptable = false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String getAnimationName() {
        return "levitate";
    }
}
