package ui.clickable;

import core.Position;
import input.mouse.MouseConsumer;
import state.State;
import ui.UIComponent;

import java.awt.Rectangle;

public abstract class UIClickable extends UIComponent implements MouseConsumer {

    protected boolean hasFocus;
    protected boolean isPressed;

    @Override
    public void update(State state) {
        Position mousePosition = state.getInput().getMousePosition();
        boolean previousFocus = hasFocus;

        hasFocus = getBounds().contains(mousePosition.intX(), mousePosition.intY());
        isPressed = hasFocus && state.getInput().isMousePressed();

//        if(hasFocus && state.getInput().isMouseClicked()) {
//            onClick(state);
//        }

        if(hasFocus && state.getInput().isMousePressed()) {
            onDrag(state);
        }

        if(!previousFocus && hasFocus) {
            onFocus(state);
        }

        if (hasFocus) {
            state.getMouseHandler().setActiveConsumer(this);
        }
    }

    protected abstract void onFocus(State state);

    private Rectangle getBounds() {
        return new Rectangle(
                absolutePosition.intX(),
                absolutePosition.intY(),
                size.getWidth(),
                size.getHeight()
        );
    }
}
