package model;

public class DefaultMovable implements Movable {

    @Override
    public ColoredPoint move(ColoredPoint point, int dx, int dy) {
        return ColoredPoint.builder()
                .setX(point.getX() + dx)
                .setY(point.getY() + dy)
                .setColor(point.getColor())
                .build();
    }

    @Override
    public ColoredPoint moveRight(ColoredPoint point, int dx) {
        return move(point, dx, 0);
    }
}
