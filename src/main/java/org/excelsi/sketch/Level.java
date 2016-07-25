package org.excelsi.sketch;


public class Level {
    private final String _name;
    private final int _ordinal;


    public Level(String name, int ordinal) {
        _name = name;
        _ordinal = ordinal;
    }

    public String getName() {
        return _name;
    }

    public int getOrdinal() {
        return _ordinal;
    }

    @Override public String toString() {
        return "level::{name:\""+_name+"\", ordinal:"+_ordinal+"}";
    }
}
