package ui.clickable;

import gfx.ImageUtils;
import map.Tile;
import state.State;
import input.mouse.action.TilePlacer;
import ui.UIImage;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class UITileToggle extends UIClickable {
    private UIImage image;
    private BufferedImage activeSprite;
    private TilePlacer tilePlacer;
    private boolean active;

    public UITileToggle(Tile tile) {
        image = new UIImage(tile.getSprite().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING));
        tilePlacer = new TilePlacer(tile);
        size = image.getSize();
        generateActiveSprite();
    }

    private void generateActiveSprite() {
        activeSprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);

        Graphics2D graphics = activeSprite.createGraphics();
        graphics.drawImage(image.getSprite(), 0, 0, null);
        graphics.setColor(new Color(0xff, 0xff, 0xff, 75));
        graphics.setComposite(AlphaComposite.SrcOver);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());

        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(2));
        graphics.drawRect(1, 1, size.getWidth()-2, size.getHeight()-2);

        graphics.dispose();
    }

    @Override
    public void update(State state) {
        super.update(state);
        active = state.getMouseHandler().getPrimaryButtonAction().equals(tilePlacer);
    }

    @Override
    public void onClick(State state) {
        state.getMouseHandler().setPrimaryButtonAction(tilePlacer);
    }

    @Override
    public void onDrag(State state) {
    }

    @Override
    public Image getSprite() {
        return active ? activeSprite : image.getSprite();
    }

    @Override
    protected void onFocus(State state) {
    }
}
