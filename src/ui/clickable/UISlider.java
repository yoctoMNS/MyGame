package ui.clickable;

import core.Size;
import gfx.ImageUtils;
import state.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UISlider extends UIClickable {
    private double value;
    private double min;
    private double max;

    public UISlider(double min, double max) {
        this.min = min;
        this.max = max;
        this.value = max;
        this.size = new Size(360, 10);
    }

    @Override
    public Image getSprite() {
        BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = sprite.createGraphics();

        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, size.w, size.h);

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getPixelsOfCurrentValue(), size.h);

        graphics.dispose();
        return sprite;
    }

    private int getPixelsOfCurrentValue() {
        double range = max - min;
        double percentage = value - min;

        return (int) (percentage / range * size.w);
    }

    @Override
    protected void onClick(State state) {
        this.value = getValueAt(state.getInput().getMousePosition().getX());
    }

    private double getValueAt(int xPosition) {
        double positionOnSlider = xPosition - absolutePosition.getX();
        double percentage = positionOnSlider / size.w;
        double range = max - min;

        return min + range * percentage;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}