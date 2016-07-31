package org.excelsi.sketch;


import org.excelsi.matrix.Matrix;


public class Level {
    private final String _name;
    private final int _ordinal;
    private final Matrix _m;


    public Level(String name, int ordinal, Matrix m) {
        _name = name;
        _ordinal = ordinal;
        _m = m;
    }

    public String getName() {
        return _name;
    }

    public int getOrdinal() {
        return _ordinal;
    }

    public Matrix getMatrix() {
        return _m;
    }

    @Override public String toString() {
        return "level::{name:\""+_name+"\", ordinal:"+_ordinal+"}";
    }
}
