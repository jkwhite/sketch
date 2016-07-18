package org.excelsi.sketch;


public class Quit implements State {
    @Override public void run(final Context c) {
        System.err.println("Be seeing you.");
        c.n().message("Be seeing you.");
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e) {
        }
        System.exit(0);
    }
}
