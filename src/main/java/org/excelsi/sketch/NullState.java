package org.excelsi.sketch;


public class NullState implements State {
    @Override public String getName() {
        return "null";
    }

    @Override public void run(final Context c) {
    }
}
