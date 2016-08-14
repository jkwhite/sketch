package org.excelsi.sketch.jfx;


import org.excelsi.sketch.Event;


@FunctionalInterface
public interface ControllerFactory {
    public static ControllerFactory constant(final Controller c) {
        return (e)->{return c;};
    }

    Controller createController(Event e);
}
