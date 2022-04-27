package model;

public class PointFactory {

    public static ColoredPoint startAtRed() {
        return ColoredPoint.builder()
                .setX(0)
                .setY(0)
                .setColor("red")
                .build();
    }

    public static Movable getMovable() {
//        return new DefaultMovable();
//        return new ColoredMovable();
//        return new ChronoMovable(new ColoredMovable());
        return new ChronoMovable(new CompositeMovable(new DefaultMovable(), new ColoredMovable()));
    }
}
