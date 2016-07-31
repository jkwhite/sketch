package org.excelsi.sketch.jfx;


import org.excelsi.sketch.Level;


public class SimpleControllerFactory implements ControllerFactory {
    @Override public Controller createController(final String type) {
        switch(type) {
            case "level":
                return new LevelController();
            default:
        }
        throw new IllegalArgumentException("uncontrollable type "+type);
    }
}
