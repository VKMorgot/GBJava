package templates.model;

public interface Movable {
    ColoredPoint move(ColoredPoint point, int dx, int dy);
    ColoredPoint moveRight(ColoredPoint point, int dx);
}
