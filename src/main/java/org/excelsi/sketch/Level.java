package org.excelsi.sketch;


import org.excelsi.matrix.Matrix;
import org.excelsi.matrix.Typed;


public interface Level extends Typed, Temporal {
    String getName();
    int getOrdinal();
    Matrix getMatrix();
}
