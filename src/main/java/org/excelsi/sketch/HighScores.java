package org.excelsi.sketch;


public class HighScores implements State {
    @Override public void run(final Context c) {
        c.n().message("High scores");
        c.n().pause();
        c.setState(new Title());
    }
}
