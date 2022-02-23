package ui;

import core.Position;
import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class UIContainer extends UIComponent {
    protected Color backgroundColor;
    protected Alignment alignment;
    protected List<UIComponent> children;
    protected Size windowSize;
    protected Size fixedSize;

    public UIContainer(Size windowSize) {
        super();

        this.backgroundColor = new Color(0, 0, 0, 0);
        this.alignment = new Alignment(Alignment.Position.START, Alignment.Position.START);
        this.margin = new Spacing(5);
        this.padding = new Spacing(5);
        this.children = new ArrayList<>();
        this.windowSize = windowSize;

        calculateSize();
        calculatePosition();
    }

    protected abstract Size calculateContentSize();
    protected abstract void calculateContentPosition();

    private void calculateSize() {
        Size calculatedContentSize = calculateContentSize();

        if (fixedSize != null) {
            size = fixedSize;
        } else {
            size = new Size (
                    padding.getHorizontal() + calculatedContentSize.w,
                    padding.getVertical() + calculatedContentSize.h
            );
        }
    }

    private void calculatePosition() {
        int x = padding.getLeft();
        if (alignment.getHorizontal().equals(Alignment.Position.CENTER)) {
            x = windowSize.w / 2 - size.w / 2;
        }
        if (alignment.getHorizontal().equals(Alignment.Position.END)) {
            x = windowSize.w - size.w - margin.getRight();
        }

        int y = padding.getTop();
        if (alignment.getVertical().equals(Alignment.Position.CENTER)) {
            y = windowSize.h / 2 - size.h / 2;
        }
        if (alignment.getVertical().equals(Alignment.Position.END)) {
            y = windowSize.h - size.h - margin.getBottom();
        }

        this.relativePosition = new Position(x, y);
        this.absolutePosition = new Position(x, y);
        calculateContentPosition();
    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, size.w, size.h);

        for (UIComponent uiComponent : children) {
            graphics.drawImage(
                    uiComponent.getSprite(),
                    uiComponent.getRelativePosition().getX(),
                    uiComponent.getRelativePosition().getY(),
                    null
            );
        }

        graphics.dispose();

        return image;
    }

    @Override
    public void update(State state) {
        children.forEach(component -> component.update(state));
        calculateSize();
        calculatePosition();
    }

    public void addUIComponent(UIComponent uiComponent) {
        children.add(uiComponent);
    }

    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void setFixedSize(Size fixedSize) {
        this.fixedSize = fixedSize;
    }
}
