package pl.jreclaw.objectpool.pool;

import pl.jreclaw.objectpool.model.calculations.SqrtPowPoint;

public class SqrtPowPool extends ObjectPool<SqrtPowPoint> {

    public SqrtPowPool(SqrtPowPoint prototype) {
        super(prototype);
    }

    @Override
    protected SqrtPowPoint create() {
        return (SqrtPowPoint) prototype.clone();
    }

    @Override
    protected void reset(SqrtPowPoint object) {
        object.setXManipulator(prototype.getXManipulator());
        object.setYManipulator(prototype.getYManipulator());
        object.setIterations(1);
        object.setMultipleResults(1);
    }
}
