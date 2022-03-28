package pl.jreclaw.objectpool.model.calculations;

import pl.jreclaw.objectpool.model.CalculationType;
import pl.jreclaw.objectpool.model.Point;

import static pl.jreclaw.objectpool.model.calculations.SqrtPoint.fancySqrt;

public class SqrtPowPoint extends PointCalc{

    public SqrtPowPoint(double xManipulator, double yManipulator) {
        super(xManipulator, yManipulator);
    }

    @Override
    public Point getCalculatedPoint(Point p) {
        Point point = new Point(p.getX(), p.getY());
        for (int i = 0 ; i < iterations ; i++) {
            point.setX(Math.pow(point.getX(), xManipulator));
            point.setY(Math.pow(point.getY(),yManipulator));
            xManipulator = point.getX();
            yManipulator = point.getY();
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
        return CalculationType.POW_SQRT;
    }
}
