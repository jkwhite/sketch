package org.excelsi.sketch;


public class SelectEvent extends Event {
    private final Menu _m;


    public SelectEvent(Object source, Menu m) {
        super(source);
        _m = m;
    }

    public Menu getMenu() {
        return _m;
    }

    @Override public String toString() {
        return "select::{menu:"+_m+"}";
    }
}
