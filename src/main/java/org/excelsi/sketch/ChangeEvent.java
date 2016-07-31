package org.excelsi.sketch;


public class ChangeEvent<E> extends Event {
    private final E _old;
    private final E _new;


    public ChangeEvent(Object source, E oldValue, E newValue) {
        super(source);
        _old = oldValue;
        _new = newValue;
    }

    public String getType() {
        return _new.getClass().getSimpleName().toLowerCase();
    }

    public E getFrom() {
        return _old;
    }

    public E getTo() {
        return _new;
    }

    @Override public String toString() {
        return "change::{from:"+_old+", to:"+_new+"}";
    }
}
