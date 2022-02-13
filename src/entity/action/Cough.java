package entity.action;

import core.CollisionBox;
import core.Position;
import core.Size;
import entity.MovingEntity;
import entity.effect.Sick;
import game.Game;
import game.GameLoop;
import game.state.State;

public class Cough extends Action {
    private int lifeSpanInSeconds;
    private Size spreadAreaSize;
    private double risk;

    public Cough() {
        this.lifeSpanInSeconds = GameLoop.UPDATES_PER_SECOND;
        this.spreadAreaSize = new Size(2 * Game.SPRITE_SIZE, 2 * Game.SPRITE_SIZE);
        this.risk = 0.1;
    }

    @Override
    public void update(State state, MovingEntity entity) {
        if (--lifeSpanInSeconds <= 0) {
            Position spreadAreaPosition = new Position(
                    entity.getPosition().x - spreadAreaSize.w / 2,
                    entity.getPosition().y - spreadAreaSize.h / 2
            );

            CollisionBox spreadArea = CollisionBox.of(spreadAreaPosition, spreadAreaSize);
            state.getGameObjectsOfClass(MovingEntity.class).stream()
                    .filter(movingEntity -> movingEntity.getCollisionBox().collidesWith(spreadArea))
                    .filter(movingEntity -> !movingEntity.isAffectedBy(Sick.class))
                    .forEach(movingEntity -> {
                        if (Math.random() < risk) {
                            movingEntity.addEffect(new Sick());
                        }
                    });
        }
    }

    @Override
    public boolean isDone() {
        return lifeSpanInSeconds == 0;
    }

    @Override
    public String getAnimationName() {
        return "cough";
    }
}
