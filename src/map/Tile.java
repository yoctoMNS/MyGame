package map;

import gfx.SpriteLibrary;
import ui.UIImage;

import java.awt.*;
import java.util.Optional;

public class Tile {

    private Image sprite;

    public Tile(SpriteLibrary spriteLibrary) {
        this(spriteLibrary, "woodfloor");
    }

    public Tile(SpriteLibrary spriteLibrary, String tileName) {
        this.sprite = spriteLibrary.getImage(tileName);
    }

    private Tile(Image sprite) {
        this.sprite = sprite;
    }

    public static Tile copyOf(Tile tile) {
        return new Tile(tile.getSprite());
    }

    public Image getSprite() {
        return sprite;
    }
}
