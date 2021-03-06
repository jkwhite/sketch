package org.excelsi.sketch.jfx;


import java.util.Map;

import org.excelsi.sketch.Event;


public class TypeControllerFactory extends Enloggened implements ControllerFactory {
    private final Map<String,ControllerFactory> _cfs;


    public TypeControllerFactory(Map<String,ControllerFactory> cfs) {
        _cfs = cfs;
    }

    @Override public Controller createController(final Event e) {
        final ControllerFactory cf = _cfs.get(e.getType());
        if(cf==null) {
            log().error("unhandled event type: "+e);
        }
        return cf.createController(e);
    }
}
