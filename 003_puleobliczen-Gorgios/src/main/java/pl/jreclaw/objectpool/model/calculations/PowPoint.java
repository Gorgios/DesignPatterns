package pl.jreclaw.objectpool.model.calculations;

import pl.jreclaw.objectpool.model.CalculationType;
import pl.jreclaw.objectpool.model.Point;

public class PowPoint extends PointCalc {

    public PowPoint(double xManipulator, double yManipulator) {
        super(xManipulator, yManipulator);
    }

    @Override
    public Point getCalculatedPoint(Point p) {
        Point point = new Point(p.getX(), p.getY());
        for (int i = 0; i < iterations * 2; i++) {
            point.setX(Math.pow(point.getX(), xManipulator));
            point.setY(Math.pow(point.getY(),yManipulator));
            xManipulator = point.getX();
            yManipulator = point.getY();
        }
        point.multipleBy(multipleResults);
        return point;
    }

    @Override
    public CalculationType getType() {
        return CalculationType.POW;
    }
}
