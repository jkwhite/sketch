package org.excelsi.sketch;


import java.util.Random;
import org.excelsi.aether.Patsy;


public class World implements State {
    private Level _level;
    private Patsy _player;
    private Bulk _bulk;
    private LevelGenerator _gen = new ExpanseLevelGenerator();


    public World() {
        _bulk = new Bulk();
    }

    @Override public void run(final Context c) {
        c.n().title("The Lower Reaches");
        _player = new Patsy();
        _player.setInputSource(c.getInputSource());
        final Level l1 = _gen.generate(
            new LevelRecipe()
            .name("The Lower Reaches")
            .ordinal(1)
            .width(80)
            .height(24)
            .random(new Random())
        );
        _bulk.addLevel(l1);
        l1.getMatrix().getSpace(0,0).setOccupant(_player);
        setLevel(l1);
        while(c.getState()==this) {
            _level.tick(c);
            c.n().pause();
        }
    }

    public void setLevel(final Level level) {
        final Level old = _level;
        _level = level;
        EventBus.instance().post("changes", new ChangeEvent<Level>(this, "level", old, _level));
        EventBus.instance().post("keys", new ChangeEvent<Level>(this, "level", old, _level));
    }
}
