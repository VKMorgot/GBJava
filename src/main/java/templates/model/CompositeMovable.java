package templates.model;

public class CompositeMovable implements Movable {

    private final Movable uncolored;
    private final Movable colored;

    public CompositeMovable(Movable uncolored, Movable colored) {
        this.uncolored = uncolored;
        this.colored = colored;
    }

    @Override
    public ColoredPoint move(ColoredPoint point, int dx, int dy) {
        if (point.getColor() == null || point.getColor().equals("black")) {
            return uncolored.move(point, dx, dy);
        } else {
            return colored.move(point, dx, dy);
        }
    }

    @Override
    public ColoredPoint moveRight(ColoredPoint point, int dx) {
        return null;
    }
}
