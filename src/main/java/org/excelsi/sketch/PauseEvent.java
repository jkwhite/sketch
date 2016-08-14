package org.excelsi.sketch;


public class PauseEvent extends Event {
    public PauseEvent(final Object source) {
        super(source);
    }

    public String getType() {
        return "pause";
    }
}
