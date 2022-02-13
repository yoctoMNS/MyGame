package controller;

import input.Input;

import java.awt.event.KeyEvent;

public class PlayerController implements EntityController {
    private Input input;

    public PlayerController(Input input) {
        this.input = input;
    }

    @Override
    public boolean isRequestiongUp() {
        return input.isCurrentlyPressed(KeyEvent.VK_UP);
    }

    @Override
    public boolean isRequestiongDown() {
        return input.isCurrentlyPressed(KeyEvent.VK_DOWN);
    }

    @Override
    public boolean isRequestiongLeft() {
        return input.isCurrentlyPressed(KeyEvent.VK_LEFT);
    }

    @Override
    public boolean isRequestiongRight() {
        return input.isCurrentlyPressed(KeyEvent.VK_RIGHT);
    }
}
