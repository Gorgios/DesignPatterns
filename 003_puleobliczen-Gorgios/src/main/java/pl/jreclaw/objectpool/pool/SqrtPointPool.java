package pl.jreclaw.objectpool.pool;

import pl.jreclaw.objectpool.model.calculations.SqrtPoint;

public class SqrtPointPool extends ObjectPool<SqrtPoint> {

    public SqrtPointPool(SqrtPoint prototype) {
        super(prototype);
    }

    @Override
    protected SqrtPoint create() {
        return (SqrtPoint) prototype.clone();
    }

    @Override
    protected void reset(SqrtPoint object) {
        object.setYManipulator(prototype.getYManipulator());
        object.setXManipulator(prototype.getXManipulator());
        object.setIterations(1);
        object.setMultipleResults(1);
    }
}
