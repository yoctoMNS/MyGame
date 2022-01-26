package ai.state;

import ai.AITransition;
import entity.NPC;
import game.state.State;

public class Stand extends AIState {
    private int updateAlive;

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("stand", ((state, currentCharacter) -> updateAlive >= state.getTime().getUpdatesFromSeconds(3)));
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        updateAlive++;
    }
}
