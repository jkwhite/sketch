package org.excelsi.sketch;


import java.util.Random;
import org.excelsi.aether.ActionCancelledException;
import org.excelsi.aether.Patsy;
import org.excelsi.aether.EventSource;


public class World implements State {
    private Stage _level;
    private Patsy _player;
    private Bulk _bulk;
    private LevelGenerator _gen = new ExpanseLevelGenerator();
    private final EventBusRelayer _relay = new EventBusRelayer();


    public World() {
        _bulk = new Bulk();
    }

    @Override public String getName() {
        return "world";
    }

    @Override public void run(final Context c) {
        c.n().title("The Lower Reaches");
        _player = new Patsy();
        _player.setInputSource(c.getInputSource());
        final Stage l1 = _gen.generate(
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
            try {
                _level.tick(c);
                //c.n().pause();
            }
            catch(ActionCancelledException e) {
            }
        }
    }

    public void setLevel(final Stage level) {
        final Stage old = _level;
        _level = level;
        EventBus.instance().post("changes", new ChangeEvent<Stage>(this, "level", old, _level));
        EventBus.instance().post("keys", new ChangeEvent<Stage>(this, "level", old, _level));
        connect(_level.getEventSource());
    }

    private void connect(final EventSource s) {
        s.addMatrixListener(_relay);
        s.addContainerListener(_relay);
        s.addNHSpaceListener(_relay);
        s.addNHEnvironmentListener(_relay);
    }
}
