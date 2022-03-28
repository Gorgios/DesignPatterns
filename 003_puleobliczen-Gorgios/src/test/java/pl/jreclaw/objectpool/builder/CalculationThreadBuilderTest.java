package pl.jreclaw.objectpool.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.jreclaw.objectpool.model.CalculationType;
import pl.jreclaw.objectpool.model.Point;
import pl.jreclaw.objectpool.pool.*;
import pl.jreclaw.objectpool.threads.CalculationThread;
import pl.jreclaw.objectpool.threads.ObjectConfig;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CalculationThreadBuilderTest {

    @Mock
    PowPointPool powPointPool;
    @Mock
    SqrtPointPool sqrtPointPool;
    @Mock
    SqrtPowPool sqrtPowPool;

    Map<CalculationType, ObjectPool> map;

    @BeforeEach
    public void setUp() {
        map = new HashMap<>();
        map.put(CalculationType.POW, powPointPool);
        map.put(CalculationType.SQRT, sqrtPointPool);
        map.put(CalculationType.POW_SQRT, sqrtPowPool);
    }

    @Test
    public void buildCorrectCalculationThread() {
        // arrange
        Point point = new Point(2, 2);
        ObjectConfig objectConfig = ObjectConfig.Builder.builder().withType(CalculationType.SQRT).build();
        // act
        CalculationThread calculationThread = CalculationThread.Builder.builder()
                .withCalculation(objectConfig)
                .withPoint(point)
                .withObjectPools(map)
                .build();

        // assert
        assertThat(calculationThread)
                .isNotNull()
                .matches(e ->
                        objectConfig.equals(e.getCalculationsConfig().getFirst())
                                && point.equals(e.getPoint())
                                && map.equals(e.getObjectPoolMap()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenObjectPoolIsNotAdequate() {
        // arrange
        Point point = new Point(2, 2);
        ObjectConfig objectConfig = ObjectConfig.Builder.builder().withType(CalculationType.SQRT).build();
        map.remove(CalculationType.SQRT);

        // act && assert
        assertThatThrownBy(() -> CalculationThread.Builder.builder()
                .withCalculation(objectConfig)
                .withPoint(point)
                .withObjectPools(map)
                .build()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void throwRuntimeExceptionWhenPointMissing() {
        // arrange
        ObjectConfig objectConfig = ObjectConfig.Builder.builder().withType(CalculationType.SQRT).build();

        // act && assert
        assertThatThrownBy( () -> CalculationThread.Builder.builder()
                .withCalculation(objectConfig)
                .withObjectPools(map)
                .build()).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void throwRuntimeExceptionWhenMapOfObjectPoolsMissing() {
        // arrange
        ObjectConfig objectConfig = ObjectConfig.Builder.builder().withType(CalculationType.SQRT).build();
        Point point = new Point(2, 2);

        // act && assert
        assertThatThrownBy( () -> CalculationThread.Builder.builder()
                .withCalculation(objectConfig)
                .withPoint(point)
                .build()).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void throwRuntimeExceptionWhenObjectConfigIsMissing() {
        // arrange
        Point point = new Point(2, 2);

        // act && assert
        assertThatThrownBy( () -> CalculationThread.Builder.builder()
                .withObjectPools(map)
                .withPoint(point)
                .build()).isInstanceOf(RuntimeException.class);
    }
}
