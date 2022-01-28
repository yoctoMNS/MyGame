package map.ui;

public class Spacing {
    private int top;
    private int bottom;
    private int left;
    private int right;

    public Spacing(int spacing) {
        this(spacing, spacing);
    }

    public Spacing(int horizontal, int vertical) {
        this(vertical, vertical, horizontal, horizontal);
    }

    public Spacing(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getHorizontal() {
        return top + bottom;
    }

    public int getVertical() {
        return left + right;
    }
}
