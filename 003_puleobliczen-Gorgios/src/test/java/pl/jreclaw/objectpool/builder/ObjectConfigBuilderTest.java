package pl.jreclaw.objectpool.builder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.jreclaw.objectpool.model.CalculationType;
import pl.jreclaw.objectpool.threads.ObjectConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ObjectConfigBuilderTest {

    @Test
    public void buildObjectConfigWithAllFields() {
        // arrange
        CalculationType type = CalculationType.POW;
        int numberOfObjects = 10;
        int iterations = 15;
        int multipleResults = 3;

        // act
        ObjectConfig objectConfig = ObjectConfig.Builder.builder()
                .withType(type)
                .withNumberOfObjects(numberOfObjects)
                .withIterations(iterations)
                .withMultipleResults(multipleResults)
                .build();

        // assert
        assertThat(objectConfig)
                .matches(e -> e.getNumberOfObjects() == numberOfObjects)
                .matches(e -> e.getIterations() == iterations)
                .matches(e -> e.getMultipleResults() == multipleResults)
                .matches(e -> CalculationType.POW.equals(e.getType()));
    }

    @Test
    public void buildObjectConfigWithOnlyType() {
        // arrange
        CalculationType type = CalculationType.POW;

        // act
        ObjectConfig objectConfig = ObjectConfig.Builder.builder()
                .withType(type)
                .build();

        // assert
        assertThat(objectConfig)
                .matches(e -> e.getNumberOfObjects() == 1)
                .matches(e -> e.getIterations() == 1)
                .matches(e -> e.getMultipleResults() == 1)
                .matches(e -> CalculationType.POW.equals(e.getType()));
    }

    @Test
    public void throwRuntimeExceptionWhenBuildWithoutType() {
        // act && assert
        Assertions.assertThatThrownBy( () -> ObjectConfig.Builder.builder().build())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNonPositiveIterations() {
        // arrange
        CalculationType type = CalculationType.POW;
        int iterations = 0;

        // act && assert
        Assertions.assertThatThrownBy( () -> ObjectConfig.Builder.builder()
                .withType(type).withIterations(iterations).build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNonPositiveNumberOfObjects() {
        // arrange
        CalculationType type = CalculationType.POW;
        int numberOfObjects = 0;

        // act && assert
        assertThatThrownBy( () -> ObjectConfig.Builder.builder()
                .withType(type).withIterations(numberOfObjects).build())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
