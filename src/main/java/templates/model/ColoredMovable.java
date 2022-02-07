package templates.model;

public class ColoredMovable implements Movable {

    @Override
    public ColoredPoint move(ColoredPoint point, int dx, int dy) {
        return ColoredPoint.builder()
                .setX(point.getX() + dx)
                .setY(point.getY() + dy)
                .setColor("strong red")
                .build();
    }

    @Override
    public ColoredPoint moveRight(ColoredPoint point, int dx) {
        return move(point, dx, 0);
    }
}
