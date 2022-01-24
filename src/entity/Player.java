package entity;

import controller.Controller;
import core.Position;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
    private Controller controller;

    public Player(Controller controller) {
        super();
        this.controller = controller;
    }

    @Override
    public void update() {
        int deltaX = 0, deltaY = 0;

        if (controller.isRequestiongUp()) {
            deltaY--;
        }
        if (controller.isRequestiongDown()) {
            deltaY++;
        }
        if (controller.isRequestiongLeft()) {
            deltaX--;
        }
        if (controller.isRequestiongRight()) {
            deltaX++;
        }

        this.position = new Position(position.getX() + deltaX, position.getY() + deltaY);
    }

    @Override
    public Image getSprite() {
        BufferedImage image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.BLUE);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());
        graphics.dispose();
        return image;
    }
}
