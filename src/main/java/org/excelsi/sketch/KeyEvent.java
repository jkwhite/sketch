package org.excelsi.sketch;


public class KeyEvent extends Event {
    private final String _k;


    public KeyEvent(Object source, String k) {
        super(source);
        _k = k;
    }

    public String getType() {
        return "key";
    }

    public String key() {
        return _k;
    }

    public String getKey() {
        return key();
    }

    @Override public String toString() {
        return "key::{k:'"+_k+"'}";
    }
}
