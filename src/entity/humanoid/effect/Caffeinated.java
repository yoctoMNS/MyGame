package entity.humanoid.effect;

import entity.humanoid.Humanoid;
import game.GameLoop;
import game.state.State;

public class Caffeinated extends Effect {
    private double speedMultiplier;

    public Caffeinated() {
        super(GameLoop.UPDATES_PER_SECOND * 5);

        this.speedMultiplier = -1;
    }

    @Override
    public void update(State state, Humanoid humanoid) {
        super.update(state, humanoid);

        humanoid.multiplySpeed(3.5);
    }
}
