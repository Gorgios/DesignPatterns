package pl.jreclaw.objectpool.pool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jreclaw.objectpool.model.calculations.SqrtPowPoint;

import static org.assertj.core.api.Assertions.assertThat;

public class SqrtPowPoolTest {
    SqrtPowPool sqrtPowPool;

    @BeforeEach
    public void setUp() {
        SqrtPowPoint sqrtPrototype = new SqrtPowPoint(2, 2);
        sqrtPowPool = new SqrtPowPool(sqrtPrototype);
    }

    @Test
    public void checkOut() {

        // act
        sqrtPowPool.checkOut();
        sqrtPowPool.checkOut();
        sqrtPowPool.checkOut();

        // assert
        assertThat(sqrtPowPool)
                .matches(e -> e.inUseSize() == 3)
                .matches(e -> e.availableSize() == 0);
    }

    @Test
    public void checkIn() {
        // arrange
        SqrtPowPoint p1, p2, p3;
        p1 = sqrtPowPool.checkOut();
        p2 = sqrtPowPool.checkOut();
        p3 = sqrtPowPool.checkOut();

        // act
        sqrtPowPool.checkIn(p1);
        sqrtPowPool.checkIn(p2);

        // assert
        assertThat(sqrtPowPool)
                .matches(e -> e.inUseSize() == 1)
                .matches(e -> e.availableSize() == 2);
    }
}
