package org.excelsi.sketch;


public class Quit implements State {
    @Override public void run(final Context c) {
        c.n().message("Be seeing you.");
        try {
            Thread.sleep(2000);
        }
        catch(InterruptedException e) {
        }
        System.exit(0);
    }
}
