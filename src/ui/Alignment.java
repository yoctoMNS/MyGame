package ui;

public class Alignment {
    public enum Position {
        START, CENTER, END;
    }

    private Position horizontal;
    private Position vertical;

    public Alignment(Position horizontal, Position vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Position getVertical() {
        return vertical;
    }

    public Position getHorizontal() {
        return horizontal;
    }
}
