package pl.jreclaw.objectpool.pool;

import pl.jreclaw.objectpool.model.calculations.PowPoint;

public class PowPointPool extends ObjectPool<PowPoint>{

    public PowPointPool(PowPoint prototype) {
        super(prototype);
    }

    @Override
    protected PowPoint create() {
        return (PowPoint) prototype.clone();
    }

    @Override
    protected void reset(PowPoint object) {
        object.setXManipulator(prototype.getXManipulator());
        object.setYManipulator(prototype.getYManipulator());
        object.setIterations(1);
        object.setMultipleResults(1);
    }
}
