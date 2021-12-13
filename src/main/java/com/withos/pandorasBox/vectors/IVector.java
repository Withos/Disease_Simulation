package com.withos.pandorasBox.vectors;

public interface IVector {
    Double abs();
    Double cdot(IVector param);
    Double[] getComponents();
}
