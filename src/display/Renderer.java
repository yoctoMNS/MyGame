package display;

import core.Position;
import game.Game;
import game.state.State;
import map.GameMap;
import map.Tile;

import java.awt.Graphics;

public class Renderer {
    public void render(State state, Graphics graphics) {
        renderMap(state, graphics);
        Camera camera = state.getCamera();

        state.getGameObjects().stream().filter(gameObject -> camera.isInView(gameObject))
                .forEach(gameObject -> graphics.drawImage(
                gameObject.getSprite(),
                gameObject.getPosition().getX() - camera.getPosition().getX() - gameObject.getSize().w / 2,
                gameObject.getPosition().getY() - camera.getPosition().getY() - gameObject.getSize().h / 2,
                null
        ));
    }

    private void renderMap(State state, Graphics graphics) {
        GameMap map = state.getGameMap();
        Camera camera = state.getCamera();
        Position start = map.getViewableStartingGridPosition(camera);
        Position end = map.getViewableEndingGridPosition(camera);

        for (int x = start.getX(); x < end.getX(); x++) {
            for (int y = start.getY(); y < end.getY(); y++) {
                graphics.drawImage(
                        map.getTiles()[x][y].getSprite(),
                        x * Game.SPRITE_SIZE - camera.getPosition().getX(),
                        y * Game.SPRITE_SIZE - camera.getPosition().getY(),
                        null);
            }
        }
    }
}
