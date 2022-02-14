package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class SelectionCircle extends GameObject{
    private Color color;
    private BufferedImage sprite;

    public SelectionCircle() {
        this.color = Color.ORANGE;
        this.size = new Size(32, 16);
        this.renderOffset = new Position(size.w / 2, size.h);
        this.renderOrder = 4;

        initializeSprite();
    }

    private void initializeSprite() {
        sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = sprite.createGraphics();

        graphics.setColor(color);
        graphics.fillOval(0, 0, size.w, size.h);

        graphics.dispose();
    }

    @Override
    public void update(State state) {
    }

    @Override
    public Image getSprite() {
        return sprite;
    }

    @Override
    public CollisionBox getCollisionBox() {
        return CollisionBox.of(getPosition(), getSize());
    }
}
