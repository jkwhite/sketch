package org.excelsi.sketch;


public class Title implements State {
    @Override public void run(final Context c) {
        c.n().title("Dawn");
        final Runnable r = c.n().choose(new SelectionMenu<Runnable>(
            new MenuItem<Runnable>("n", "New game", null),
            new MenuItem<Runnable>("l", "Load game", null),
            new MenuItem<Runnable>("h", "High scores", ()->{ c.setState(new HighScores()); }),
            new MenuItem<Runnable>("q", "Quit", ()->{ c.setState(new Quit()); })
        ));
        r.run();
    }
}
