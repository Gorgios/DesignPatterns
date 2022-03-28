package pl.jreclaw.objectpool.pool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jreclaw.objectpool.model.calculations.PowPoint;

import static org.assertj.core.api.Assertions.assertThat;

public class PowPoolTest {

    PowPointPool powPointPool;

    @BeforeEach
    public void setUp() {
        PowPoint powPrototype = new PowPoint(2, 2);
        powPointPool = new PowPointPool(powPrototype);
    }

    @Test
    public void checkOut() {

        // act
        powPointPool.checkOut();
        powPointPool.checkOut();
        powPointPool.checkOut();

        // assert
        assertThat(powPointPool)
                .matches(e -> e.inUseSize() == 3)
                .matches(e -> e.availableSize() == 0);
    }

    @Test
    public void checkIn() {
        // arrange
        PowPoint p1, p2, p3;
        p1 = powPointPool.checkOut();
        p2 = powPointPool.checkOut();
        p3 = powPointPool.checkOut();

        // act
        powPointPool.checkIn(p1);
        powPointPool.checkIn(p2);

        // assert
        assertThat(powPointPool)
                .matches(e -> e.inUseSize() == 1)
                .matches(e -> e.availableSize() == 2);
    }
}
