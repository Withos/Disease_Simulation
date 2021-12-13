package com.withos.pandorasBox.Vectors;

public interface IVector {
    Double abs();
    Double cdot(IVector param);
    Double[] getComponents();
}
