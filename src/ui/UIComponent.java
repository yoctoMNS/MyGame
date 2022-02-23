package ui;

import core.Position;
import core.Size;
import game.state.State;

import java.awt.Image;

public abstract class UIComponent {
    protected Position relativePosition;
    protected Position absolutePosition;
    protected Size size;
    protected Spacing margin;
    protected Spacing padding;

    public UIComponent() {
        this.relativePosition = new Position(0, 0);
        this.absolutePosition = new Position(0, 0);
        this.size = new Size(1, 1);
        this.margin = new Spacing(0);
        this.padding = new Spacing(0);
    }

    public abstract Image getSprite();

    public abstract void update(State state);

    public Position getRelativePosition() {
        return relativePosition;
    }

    public void setRelativePosition(Position relativePosition) {
        this.relativePosition = relativePosition;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Spacing getMargin() {
        return margin;
    }

    public void setMargin(Spacing margin) {
        this.margin = margin;
    }

    public Spacing getPadding() {
        return padding;
    }

    public void setPadding(Spacing padding) {
        this.padding = padding;
    }

    public Position getAbsolutePosition() {
        return absolutePosition;
    }

    public void setAbsolutePosition(Position absolutePosition) {
        this.absolutePosition = absolutePosition;
    }
}
