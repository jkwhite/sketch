package org.excelsi.sketch.jfx;


import org.excelsi.sketch.Level;
import org.excelsi.sketch.Event;


public class SimpleControllerFactory implements ControllerFactory {
    @Override public Controller createController(final Event e) {
        switch(e.getType()) {
            case "bot":
                return new BotController();
            case "level":
                return new LevelController();
            default:
        }
        throw new IllegalArgumentException("uncontrollable type "+e.getType());
    }
}
