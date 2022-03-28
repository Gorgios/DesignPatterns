package pl.jreclaw.objectpool.threads;

import pl.jreclaw.objectpool.model.CalculationType;
import pl.jreclaw.objectpool.model.Point;
import pl.jreclaw.objectpool.model.calculations.PowPoint;
import pl.jreclaw.objectpool.model.calculations.SqrtPoint;
import pl.jreclaw.objectpool.model.calculations.SqrtPowPoint;
import pl.jreclaw.objectpool.pool.ObjectPool;
import pl.jreclaw.objectpool.pool.PowPointPool;
import pl.jreclaw.objectpool.pool.SqrtPointPool;
import pl.jreclaw.objectpool.pool.SqrtPowPool;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Executor {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        Map<CalculationType, ObjectPool> map = new HashMap<>();
        map.put(CalculationType.POW, new PowPointPool(new PowPoint(2,2)));
        map.put(CalculationType.SQRT, new SqrtPointPool(new SqrtPoint(2,2)));
        map.put(CalculationType.POW_SQRT, new SqrtPowPool(new SqrtPowPoint(2,2)));
        List<CalculationThread> threadList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            CalculationThread calculationThread = CalculationThread.Builder.builder()
                    .withPoint(new Point(new Random().nextDouble(), new Random().nextDouble()))
                    .withCalculation(ObjectConfig.Builder.builder().
                            withType(CalculationType.POW).withNumberOfObjects(3).withIterations(1000).build())
                    .withCalculation(ObjectConfig.Builder.builder().
                            withType(CalculationType.SQRT).withNumberOfObjects(2).withIterations(1000).build())
                    .withCalculation(ObjectConfig.Builder.builder().
                            withType(CalculationType.SQRT).withNumberOfObjects(1).withIterations(1000).build())
                    .withCalculation(ObjectConfig.Builder.builder().
                            withType(CalculationType.POW_SQRT).withNumberOfObjects(2).withIterations(1000).build())
                    .withObjectPools(map)
                    .build();
            threadList.add(calculationThread);
        }
        for (int i = 0; i < 10000; i++ ){
            executorService.invokeAll(threadList);
        }
        executorService.shutdown();
    }
}
