package org.excelsi.sketch;


public interface State {
    void run(Context c);
    String getName();
}
