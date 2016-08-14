package org.excelsi.sketch;


public abstract class Event {
    private final Object _source;


    public Event(final Object source) {
        _source = source;
    }

    public abstract String getType();
}
