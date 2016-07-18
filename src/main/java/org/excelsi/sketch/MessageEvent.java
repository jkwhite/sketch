package org.excelsi.sketch;


public class MessageEvent extends Event {
    private final String _m;


    public MessageEvent(Object source, String m) {
        super(source);
        _m = m;
    }

    public String getMessage() {
        return _m;
    }
}
