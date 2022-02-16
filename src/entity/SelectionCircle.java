package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class SelectionCircle extends GameObject{
    private Color color;
    private BufferedImage sprite;

    public SelectionCircle() {
        this.color = new Color(0, 255, 255);
        this.size = new Size(20, 16);
        this.renderOffset = new Position(size.w / 2, size.h);
        this.collisionBoxOffset = renderOffset;
        this.renderOrder = 4;

        initializeSprite();
    }

    private void initializeSprite() {
        sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = sprite.createGraphics();

        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(2));
        graphics.drawOval(0, 0, size.w, size.h);

        graphics.dispose();
    }

    @Override
    public void update(State state) {
    }

    @Override
    public Image getSprite() {
        return parent != null ? sprite : null;
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position position = getPosition();
        position.subtract(collisionBoxOffset);

        return CollisionBox.of(position, getSize());
    }
}
