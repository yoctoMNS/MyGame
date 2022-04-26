package map;

import core.Position;
import core.Size;
import display.Camera;
import game.Game;
import gfx.SpriteLibrary;
import io.Persistable;

import java.io.Serializable;
import java.util.Arrays;

public class GameMap implements Persistable {

    private static final int SAFETY_SPACE = 2;

    private Tile[][] tiles;

    public GameMap() {}

    public GameMap(Size size, SpriteLibrary spriteLibrary) {
        tiles = new Tile[size.getWidth()][size.getHeight()];
        initializeTiles(spriteLibrary);
    }

    private void initializeTiles(SpriteLibrary spriteLibrary) {
        for(Tile[] row: tiles) {
            Arrays.fill(row, new Tile(spriteLibrary));
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return tiles.length * Game.SPRITE_SIZE;
    }

    public int getHeight() {
        return tiles[0].length * Game.SPRITE_SIZE;
    }

    public Position getRandomPosition() {
        double x = Math.random() * tiles.length * Game.SPRITE_SIZE;
        double y = Math.random() * tiles[0].length * Game.SPRITE_SIZE;

        return new Position(x, y);
    }

    public Position getViewableStartingGridPosition(Camera camera) {
        return new Position(
                Math.max(0, camera.getPosition().getX() / Game.SPRITE_SIZE - SAFETY_SPACE),
                Math.max(0, camera.getPosition().getY() / Game.SPRITE_SIZE - SAFETY_SPACE)
        );
    }

    public Position getViewableEndingGridPosition(Camera camera) {
        double cameraXPos = camera.getPosition().getX() / Game.SPRITE_SIZE + camera.getSize().getWidth() / Game.SPRITE_SIZE + SAFETY_SPACE;
        double cameraYPos = camera.getPosition().getY() / Game.SPRITE_SIZE + camera.getSize().getHeight() / Game.SPRITE_SIZE + SAFETY_SPACE;
        return new Position(
                Math.min(tiles.length, cameraXPos),
                Math.min(tiles[0].length, cameraYPos)
        );
    }

    public boolean gridWithinBounds(int gridX, int gridY) {
        return gridX>=0 && gridX<tiles.length &&
                gridY>=0 && gridY<tiles[0].length;
    }

    public void setTile(int gridX, int gridY, Tile tile) {
        tiles[gridX][gridY] = tile;
    }

    public void reloadGraphics(SpriteLibrary spriteLibrary) {
        for (Tile[] row : tiles)
            for (Tile tile : row)
                tile.reloadGraphics(spriteLibrary);
    }

    @Override
    public String serialize() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(tiles.length);
        stringBuilder.append(DELIMITER);
        stringBuilder.append(tiles[0].length);
        stringBuilder.append(DELIMITER);

        stringBuilder.append(SECTION_DELIMITER);
        for (int x=0; x<tiles.length; x++) {
            for (int y=0; y<tiles[0].length; y++) {
                stringBuilder.append(tiles[x][y].serialize());
                stringBuilder.append(LIST_DELIMITER);
            }

            stringBuilder.append(COLUMN_DELIMITER);
        }

        return stringBuilder.toString();
    }

    @Override
    public void applySerializedData(String serializedData) {
        String[] tokens = serializedData.split(DELIMITER);
        tiles = new Tile[Integer.parseInt(tokens[1])][Integer.parseInt(tokens[2])];

        String tileSection = serializedData.split(SECTION_DELIMITER)[1];
        String[] columns = tileSection.split(COLUMN_DELIMITER);

        for (int x=0; x<tiles.length; x++) {
            String[] serializedTiles = columns[x].split(LIST_DELIMITER);
            for (int y=0; y<tiles[0].length; y++) {
                Tile tile = new Tile();
                tile.applySerializedData(serializedTiles[y]);
                tiles[x][y] = tile;
            }
        }
    }
}
