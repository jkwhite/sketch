package org.excelsi.sketch;


public class World implements State {
    private Level _level;


    @Override public void run(final Context c) {
        c.n().title("The Lower Reaches");
        setLevel(new Level("The Lower Reaches", 1));
        c.n().pause();
    }

    public void setLevel(final Level level) {
        final Level old = _level;
        _level = level;
        EventBus.instance().post(new ChangeEvent<Level>(this, old, _level));
    }
}
