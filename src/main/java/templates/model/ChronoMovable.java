package templates.model;

public class ChronoMovable implements Movable {

    private final Movable movable;

    public ChronoMovable (Movable movable) {
        this.movable = movable;
    }

    @Override
    public ColoredPoint move(ColoredPoint point, int dx, int dy) {
        long start = System.currentTimeMillis();
        ColoredPoint point1 = movable.move(point, dx, dy);
        System.out.println("Execution time = " + (System.currentTimeMillis() - start));
        return point1;
    }

    @Override
    public ColoredPoint moveRight(ColoredPoint point, int dx) {
        return movable.moveRight(point, dx);
    }
}
