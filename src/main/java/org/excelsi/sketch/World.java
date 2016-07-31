package org.excelsi.sketch;


import java.util.Random;


public class World implements State {
    private Level _level;
    private LevelGenerator _gen = new ExpanseLevelGenerator();


    @Override public void run(final Context c) {
        c.n().title("The Lower Reaches");
        setLevel(_gen.generate(
            new LevelRecipe()
            .name("The Lower Reaches")
            .ordinal(1)
            .width(80)
            .height(24)
            .random(new Random())
        ));
        while(true) {
            c.n().pause();
        }
    }

    public void setLevel(final Level level) {
        final Level old = _level;
        _level = level;
        EventBus.instance().post(new ChangeEvent<Level>(this, old, _level));
    }
}
