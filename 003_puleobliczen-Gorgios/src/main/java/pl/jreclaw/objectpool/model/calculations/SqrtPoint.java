package pl.jreclaw.objectpool.model.calculations;

import pl.jreclaw.objectpool.model.CalculationType;
import pl.jreclaw.objectpool.model.Point;

import java.util.Random;

public class SqrtPoint extends PointCalc {

    public SqrtPoint(double xManipulator, double yManipulator) {
        super(xManipulator, yManipulator);
    }

    @Override
    public Point getCalculatedPoint(Point p) {
        Point point = new Point(p.getX(), p.getY());
        for (int i = 0; i < iterations * 2 ; i++) {
            point.setX(fancySqrt(point.getX(), xManipulator));
            point.setY(fancySqrt(point.getY(), yManipulator));
            xManipulator = point.getX();
            yManipulator = point.getY();
        }
        point.multipleBy(multipleResults);
        return point;
    }

    @Override
    public CalculationType getType() {
        return CalculationType.SQRT;
    }

    protected static double fancySqrt(double value, double manipulator) {
        return Math.pow(value, 1 / manipulator);
    }
}
