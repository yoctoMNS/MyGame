package ui.clickable;

import core.Position;
import game.state.State;
import ui.UIComponent;

import java.awt.Rectangle;

public abstract class UIClickable extends UIComponent {
    protected boolean hasFocus;
    protected boolean isPressed;

    @Override
    public void update(State state) {
        Position mousePosition = state.getInput().getMousePosition();
        hasFocus = getBounds().contains(mousePosition.getX(), mousePosition.getY());
        isPressed = hasFocus && state.getInput().isMousePressed();

        if (hasFocus && state.getInput().isMouseClicked()) {
            onClick();
        }
    }

    protected abstract void onClick();

    public Rectangle getBounds() {
        return new Rectangle(
                absolutePosition.getX(),
                absolutePosition.getY(),
                size.w,
                size.h
        );
    }
}
