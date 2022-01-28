package display;

import core.CollisionBox;
import game.state.State;

import java.awt.Color;
import java.awt.Graphics;

public class DebugRenderer {
    public void render(State state, Graphics graphics) {
        Camera camera = state.getCamera();

        state.getGameObjects()
             .stream()
             .filter(gameObject -> camera.isInView(gameObject))
             .map(gameObject -> gameObject.getCollisionBox())
             .forEach(collisionBox -> drawCollisionBox(collisionBox, graphics, camera));
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
