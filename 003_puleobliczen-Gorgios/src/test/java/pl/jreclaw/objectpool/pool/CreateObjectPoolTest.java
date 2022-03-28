package pl.jreclaw.objectpool.pool;

import org.junit.jupiter.api.Test;
import pl.jreclaw.objectpool.model.calculations.PowPoint;
import pl.jreclaw.objectpool.model.calculations.SqrtPoint;
import pl.jreclaw.objectpool.model.calculations.SqrtPowPoint;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateObjectPoolTest {

    @Test
    public void createPowPool() {
        // arrange
        PowPoint powPrototype = new PowPoint(2, 2);

        // act
        PowPointPool powPointPool = new PowPointPool(powPrototype);

        // assert
        assertThat(powPointPool)
                .isNotNull()
                .isInstanceOf(PowPointPool.class);
        assertThat(powPointPool.prototype)
                .isEqualTo(powPrototype);
    }

    @Test
    public void createSqrtPool() {
        // arrange
        SqrtPoint sqrtPrototype = new SqrtPoint(2, 2);

        // act
        SqrtPointPool sqrtPointPool = new SqrtPointPool(sqrtPrototype);

        // assert
        assertThat(sqrtPointPool)
                .isNotNull()
                .isInstanceOf(SqrtPointPool.class);
        assertThat(sqrtPointPool.prototype)
                .isEqualTo(sqrtPrototype);
    }

    @Test
    public void createSqrtPowPool() {
        // arrange
        SqrtPowPoint sqrtPowPrototype = new SqrtPowPoint(2, 2);

        // act
        SqrtPowPool sqrtPowPool = new SqrtPowPool(sqrtPowPrototype);

        // assert
        assertThat(sqrtPowPool)
                .isNotNull()
                .isInstanceOf(SqrtPowPool.class);
        assertThat(sqrtPowPool.prototype)
                .isEqualTo(sqrtPowPrototype);
    }
}
