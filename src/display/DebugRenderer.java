package display;

import core.CollisionBox;
import entity.humanoid.Humanoid;
import state.State;
import ui.UIText;

import java.awt.Color;
import java.awt.Graphics;
import java.util.stream.Collectors;

public class DebugRenderer {
    public void render(State state, Graphics graphics) {
        Camera camera = state.getCamera();

        state.getGameObjects()
             .stream()
             .filter(gameObject -> camera.isInView(gameObject))
             .map(gameObject -> gameObject.getCollisionBox())
             .forEach(collisionBox -> drawCollisionBox(collisionBox, graphics, camera));

        drawEffects(state, graphics);
    }

    private void drawEffects(State state, Graphics graphics) {
        Camera camera = state.getCamera();

        state.getGameObjectsOfClass(Humanoid.class).stream()
                .forEach(humanoid -> {
                    UIText effectsText = new UIText(
                            humanoid.getEffects().stream()
                                    .map(effect -> effect.getClass().getSimpleName())
                                    .collect(Collectors.joining(","))
                    );
                    effectsText.update(state);

                    graphics.drawImage(
                            effectsText.getSprite(),
                            humanoid.getPosition().getX() - camera.getPosition().getX(),
                            humanoid.getPosition().getY() - camera.getPosition().getY(),
                            null
                    );
                });
    }

    private void drawCollisionBox(CollisionBox collisionBox, Graphics graphics, Camera camera) {
        graphics.setColor(Color.RED);
        graphics.drawRect(
                collisionBox.getBounds().x - camera.getPosition().getX(),
                collisionBox.getBounds().y - camera.getPosition().getY(),
                collisionBox.getBounds().width,
                collisionBox.getBounds().height
        );
    }
}
