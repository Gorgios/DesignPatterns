package pl.jreclaw.objectpool.threads;

import lombok.Getter;
import pl.jreclaw.objectpool.model.CalculationType;
import pl.jreclaw.objectpool.model.Point;
import pl.jreclaw.objectpool.model.calculations.PointCalc;
import pl.jreclaw.objectpool.pool.ObjectPool;

import java.util.*;
import java.util.concurrent.Callable;

@Getter
public class CalculationThread implements Callable<Point> {

    private final LinkedList<ObjectConfig> calculationsConfig;
    private final Map<CalculationType, ObjectPool> objectPoolMap;
    private final LinkedList<PointCalc> calculationsToExecute;
    private Point point;

    private CalculationThread(Builder builder) {
        this.calculationsConfig = builder.calculationsConfig;
        this.objectPoolMap = builder.objectPoolMap;
        this.point = builder.point;
        this.calculationsToExecute = new LinkedList<>();
    }

    @Override
    public Point call() {
        for (ObjectConfig config : calculationsConfig) {
            ObjectPool objectPool = objectPoolMap.get(config.getType());
            for (int i = 0; i < config.getNumberOfObjects(); i++) {
                PointCalc calc = (PointCalc) objectPool.checkOut();
                calc.withIterations(config.getIterations());
                calc.withMultipleResults(config.getMultipleResults());
                calculationsToExecute.addLast(calc);
            }
        }
        for (PointCalc calc : calculationsToExecute) {
            this.point = calc.getCalculatedPoint(this.point);
            objectPoolMap.get(calc.getType()).checkIn(calc);
        }
        return this.point;
    }

    public static class Builder {
        LinkedList<ObjectConfig> calculationsConfig = new LinkedList<>();
        Map<CalculationType, ObjectPool> objectPoolMap;
        Point point;

        public static Builder builder() {
            return new Builder();
        }

        public Builder withPoint(Point point) {
            this.point = point;
            return this;
        }

        public Builder withObjectPools(Map<CalculationType, ObjectPool> objectPoolMap) {
            if (!objectPoolMap.keySet().containsAll(Arrays.asList(CalculationType.values()))) {
                throw new IllegalArgumentException("Invalid objectPoolMap");
            }
            this.objectPoolMap = objectPoolMap;
            return this;
        }

        public Builder withCalculation(ObjectConfig calculationConfig) {
            calculationsConfig.addLast(calculationConfig);
            return this;
        }

        public CalculationThread build() {
            if (point == null) {
                throw new RuntimeException("Point must be set!");
            }
            if (objectPoolMap == null) {
                throw new RuntimeException("Object pool map must be set!");
            }
            if (calculationsConfig.isEmpty()) {
                throw new RuntimeException("Nothing to execute, add at least one calculation config!");
            }
            return new CalculationThread(this);
        }
    }
}
