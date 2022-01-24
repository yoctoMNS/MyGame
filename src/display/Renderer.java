package display;

import game.Game;

import java.awt.Graphics;

public class Renderer {
    public void render(Game game, Graphics graphics) {
        game.getGameObjects().forEach(gameObject -> graphics.drawImage(
                gameObject.getSprite(),
                gameObject.getPosition().getX(),
                gameObject.getPosition().getY(),
                gameObject.getSize().getWidth(),
                gameObject.getSize().getHeight(),
                null
        ));
    }
}
