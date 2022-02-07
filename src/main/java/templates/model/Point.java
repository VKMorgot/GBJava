package templates.model;

public class Point {

    private final int x;
    private final int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // static
    public static Point startPoint() {
        return new Point(0, 0);
    }
}
