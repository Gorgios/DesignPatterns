package pl.jreclaw.objectpool.thread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jreclaw.objectpool.model.CalculationType;
import pl.jreclaw.objectpool.model.Point;
import pl.jreclaw.objectpool.model.calculations.PowPoint;
import pl.jreclaw.objectpool.model.calculations.SqrtPoint;
import pl.jreclaw.objectpool.model.calculations.SqrtPowPoint;
import pl.jreclaw.objectpool.pool.ObjectPool;
import pl.jreclaw.objectpool.pool.PowPointPool;
import pl.jreclaw.objectpool.pool.SqrtPointPool;
import pl.jreclaw.objectpool.pool.SqrtPowPool;
import pl.jreclaw.objectpool.threads.CalculationThread;
import pl.jreclaw.objectpool.threads.ObjectConfig;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class ThreadExecutingTests {
    ExecutorService executorService;
    Map<CalculationType, ObjectPool> map = new HashMap<>();

    @BeforeEach
    public void setUp() {
        map = new HashMap<>();
        map.put(CalculationType.POW, new PowPointPool(new PowPoint(2, 2)));
        map.put(CalculationType.SQRT, new SqrtPointPool(new SqrtPoint(2, 2)));
        map.put(CalculationType.POW_SQRT, new SqrtPowPool(new SqrtPowPoint(2, 2)));
    }

    @Test
    public void chceckFunctionReturnGoodResult() {
        // arrange
        CalculationThread calculationThread = CalculationThread.Builder.builder()
                .withPoint(new Point(2, 2))
                .withCalculation(ObjectConfig.Builder.builder().
                        withType(CalculationType.POW).build())
                .withObjectPools(map).build();

        // act
        Point point = calculationThread.call();

        // assert
        assertThat(point.getX()).isEqualTo(Math.pow(2 * 2, 2 * 2));
        assertThat(point.getY()).isEqualTo(Math.pow(2 * 2, 2 * 2));
    }

    @Test
    public void chceckFunctionReturnGoodResultFor2Calcs() {
        // arrange
        CalculationThread calculationThread = CalculationThread.Builder.builder()
                .withPoint(new Point(2, 2))
                .withCalculation(ObjectConfig.Builder.builder().
                        withType(CalculationType.POW).build())
                .withCalculation(ObjectConfig.Builder.builder().
                        withType(CalculationType.SQRT).build())
                .withObjectPools(map).build();

        // act
        Point point = calculationThread.call();

        // assert -> brzydkie testy wiem, ale malo czasu
        double afterPow = 256;
        assertThat(point.getX()).isEqualTo(Math.pow(Math.pow(256,(double)1/2),1/Math.pow(256,(double)1/2)));
        assertThat(point.getY()).isEqualTo(Math.pow(Math.pow(256,(double)1/2),1/Math.pow(256,(double)1/2)));
    }

    @Test
    public void executeThreadWithManyCalcs() {
        // arrange
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

        // act
        Point point = calculationThread.call();

        // assert
        assertThat(point)
                .isNotNull();
    }

    @Test
    public void execute100Threads() throws InterruptedException {
        //arrange
        executorService = Executors.newFixedThreadPool(100);
        List<CalculationThread> list = createThreadsWithPowCalc(100);

        //act
        executorService.invokeAll(list);
        executorService.shutdown();

        //then
        assertThat(map.get(CalculationType.POW))
                .matches(e -> e.inUseSize() == 0 && e.availableSize() >= 1);
    }

    @Test
    public void execute1000Threads() throws InterruptedException {
        //arrange
        executorService = Executors.newFixedThreadPool(1000);
        List<CalculationThread> list = createThreadsWithPowCalc(1000);

        //act
        executorService.invokeAll(list);
        executorService.shutdown();

        //then
        assertThat(map.get(CalculationType.POW))
                .matches(e -> e.inUseSize() == 0 && e.availableSize() >= 1);
    }

    @Test
    public void execute10000Threads() throws InterruptedException {
        //arrange
        executorService = Executors.newFixedThreadPool(10000);
        List<CalculationThread> list = createThreadsWithPowCalc(10000);

        //act
        executorService.invokeAll(list);
        executorService.shutdown();

        //then
        assertThat(map.get(CalculationType.POW))
                .matches(e -> e.inUseSize() == 0 && e.availableSize() >= 1);
    }


    private List<CalculationThread> createThreadsWithPowCalc(int howMany) {
        List<CalculationThread> threadList = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            CalculationThread calculationThread = CalculationThread.Builder.builder()
                    .withPoint(new Point(new Random().nextDouble(), new Random().nextDouble()))
                    .withCalculation(ObjectConfig.Builder.builder().
                            withType(CalculationType.POW).
                            withNumberOfObjects(new Random().nextInt(5) + 1)
                            .withIterations(new Random().nextInt(1000) + 1).build())
                    .withObjectPools(map)
                    .build();
            threadList.add(calculationThread);
        }
        return threadList;
    }
}
