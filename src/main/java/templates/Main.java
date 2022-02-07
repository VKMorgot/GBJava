package templates;

import templates.model.*;

public class Main {

    public static void main(String[] args) {
    // write your code here
        Point point = Point.startPoint();

        ColoredPoint coloredPoint = ColoredPoint.builder().setX(0).setY(0).build();

        ColoredPoint red = PointFactory.startAtRed();


        // от обычного создания переходим к созданию через фабрику
//        Movable movable = new DefaultMovable();
        Movable movable = PointFactory.getMovable();
        red = movable.move(red, 10, 10);
    }
}
