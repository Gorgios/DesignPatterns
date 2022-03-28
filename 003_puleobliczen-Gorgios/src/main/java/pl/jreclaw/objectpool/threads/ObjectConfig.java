package pl.jreclaw.objectpool.threads;

import lombok.Getter;
import pl.jreclaw.objectpool.model.CalculationType;

@Getter
public class ObjectConfig {

    private CalculationType type;
    private int numberOfObjects;
    private int iterations;
    private int multipleResults;

    private ObjectConfig(Builder builder) {
        this.type = builder.type;
        this.numberOfObjects = builder.numberOfObjects;
        this.iterations = builder.iterations;
        this.multipleResults = builder.multipleResults;
    }

    public static class Builder {
        CalculationType type;
        int numberOfObjects = 1;
        int iterations = 1;
        int multipleResults = 1;

        private Builder(){};

        public static Builder builder() {
            return new Builder();
        }

        public Builder withType(CalculationType type) {
            this.type = type;
            return this;
        }

        public Builder withNumberOfObjects(int numberOfObjects) {
            if (numberOfObjects < 1) {
                throw new IllegalArgumentException("Number of objects must be positive!");
            }
            this.numberOfObjects = numberOfObjects;
            return this;
        }

        public Builder withIterations(int iterations) {
            if (iterations < 1) {
                throw new IllegalArgumentException("Iterations must be positive!");
            }
            this.iterations = iterations;
            return this;
        }

        public Builder withMultipleResults(int multipleResults) {
            this.multipleResults = multipleResults;
            return this;
        }

        public ObjectConfig build(){
            if (type == null) {
                throw new RuntimeException("Calculation type must be set!");
            }
            return new ObjectConfig(this);
        }
    }
}
