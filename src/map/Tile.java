package map;

import gfx.SpriteLibrary;

import java.awt.Image;

public class Tile {
    private Image sprite;

    public Tile(SpriteLibrary spriteLibrary) {
        sprite = spriteLibrary.getTile("woodfloor");
    }

    public Image getSprite() {
        return sprite;
    }
}
