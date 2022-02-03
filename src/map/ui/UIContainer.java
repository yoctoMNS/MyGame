package map.ui;

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
    protected List<UIComponent> children;

    public UIContainer() {
        super();

        this.backgroundColor = Color.RED;
        this.margin = new Spacing(5);
        this.children = new ArrayList<>();

        calculateSize();
        calculatePosition();
    }

    protected abstract Size calculateContentSize();
    protected abstract void calculateContentPosition();

    private void calculateSize() {
        Size calculatedContentSize = calculateContentSize();

        size = new Size(
                padding.getHorizontal() + calculatedContentSize.w,
                padding.getVertical() + calculatedContentSize.h
        );
    }

    private void calculatePosition() {
        position = new Position(margin.getLeft(), margin.getTop());
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
                    uiComponent.getPosition().getX(),
                    uiComponent.getPosition().getY(),
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

    public void getBackgroundColor(Color color) {
        backgroundColor = color;
    }
}
