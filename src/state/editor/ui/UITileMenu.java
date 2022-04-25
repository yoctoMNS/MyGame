package state.editor.ui;

import core.Size;
import game.Game;
import gfx.SpriteLibrary;
import map.Tile;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.Spacing;
import ui.UIComponent;
import ui.UIContainer;
import ui.VerticalContainer;
import ui.clickable.UITileToggle;

import java.awt.Color;

public class UITileMenu extends HorizontalContainer {
    public UITileMenu(Size windowSize, SpriteLibrary spriteLibrary) {
        super(windowSize);
        setBackgroundColor(Color.DARK_GRAY);
        setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.END));

        addUIComponent(new UITileToggle(new Tile(spriteLibrary, "grass")));
        addUIComponent(getTileSet(spriteLibrary, "concrete"));
        addUIComponent(getTileSet(spriteLibrary, "dirt"));
    }

    private UIComponent getTileSet(SpriteLibrary spriteLibrary, String tileset) {
        UIContainer main = new HorizontalContainer(new Size(0, 0));
        main.setMargin(new Spacing(0));
        main.setPadding(new Spacing(0));
        Tile tile = new Tile(spriteLibrary, tileset);

        int tileX = tile.getImage().getWidth(null) / Game.SPRITE_SIZE;
        int tileY = tile.getImage().getHeight(null) / Game.SPRITE_SIZE;

        for (int x=0; x<tileX; x++) {
            UIContainer column = new VerticalContainer(new Size(0, 0));
            column.setPadding(new Spacing(0));
            column.setMargin(new Spacing(0));

            for (int y=0; y<tileY; y++) {
                Tile indexedTile = Tile.copyOf(tile);
                indexedTile.setTileIndex(x*tileX + y);
                UITileToggle toggle = new UITileToggle(indexedTile);
                column.addUIComponent(toggle);
            }

            main.addUIComponent(column);
        }

        return main;
    }
}
