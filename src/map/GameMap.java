package map;

import core.Size;
import gfx.SpriteLibrary;

import java.util.Arrays;

public class GameMap {
    private Tile[][] tiles;

    public GameMap(Size size, SpriteLibrary spriteLibrary) {
        this.tiles = new Tile[size.w][size.h];
        initializeTiles(spriteLibrary);
    }

    private void initializeTiles(SpriteLibrary spriteLibrary) {
        for (Tile[] row : tiles) {
            Arrays.fill(row, new Tile(spriteLibrary));
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
