package pl.jreclaw.objectpool.pool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jreclaw.objectpool.model.calculations.SqrtPoint;

import static org.assertj.core.api.Assertions.assertThat;

public class SqrtPoolTest {
    SqrtPointPool sqrtPointPool;

    @BeforeEach
    public void setUp() {
        SqrtPoint sqrtPrototype = new SqrtPoint(2, 2);
        sqrtPointPool = new SqrtPointPool(sqrtPrototype);
    }

    @Test
    public void checkOut() {
        // act
        sqrtPointPool.checkOut();
        sqrtPointPool.checkOut();
        sqrtPointPool.checkOut();

        // assert
        assertThat(sqrtPointPool)
                .matches(e -> e.inUseSize() == 3)
                .matches(e -> e.availableSize() == 0);
    }

    @Test
    public void checkIn() {
        // arrange
        SqrtPoint p1, p2, p3;
        p1 = sqrtPointPool.checkOut();
        p2 = sqrtPointPool.checkOut();
        p3 = sqrtPointPool.checkOut();

        // act
        sqrtPointPool.checkIn(p1);
        sqrtPointPool.checkIn(p2);

        // assert
        assertThat(sqrtPointPool)
                .matches(e -> e.inUseSize() == 1)
                .matches(e -> e.availableSize() == 2);
    }
}
