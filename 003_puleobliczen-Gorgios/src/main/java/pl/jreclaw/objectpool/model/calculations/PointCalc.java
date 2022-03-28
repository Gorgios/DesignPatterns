package pl.jreclaw.objectpool.model.calculations;

import lombok.*;
import pl.jreclaw.objectpool.model.CalculationType;
import pl.jreclaw.objectpool.model.Point;

@RequiredArgsConstructor
@Getter
@Setter
public abstract class PointCalc implements Cloneable {

    @NonNull
    protected double xManipulator;
    @NonNull
    protected double yManipulator;
    protected int iterations = 1;
    protected int multipleResults = 1;

    public abstract Point getCalculatedPoint(Point p);

    public abstract CalculationType getType();

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();

        } catch ( CloneNotSupportedException e ) {
            e.printStackTrace();
        }

        return clone;
    }
    public void withIterations(int iterations) {
        this.iterations = iterations;
    }

    public void withMultipleResults(int multipleResults) {
        this.multipleResults = multipleResults;
    }
}
