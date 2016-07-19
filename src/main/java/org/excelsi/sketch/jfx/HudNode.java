package org.excelsi.sketch.jfx;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.event.EventHandler;


public abstract class HudNode extends Parent {
    private List<EventHandler<LogicEvent>> _logicHandlers;


    public final void addLogicHandler(final EventHandler<LogicEvent> h) {
        if(_logicHandlers==null) {
            _logicHandlers = new ArrayList<>(1);
        }
        _logicHandlers.add(h);
    }

    public final void onEvent(LogicEvent le) {
        if(_logicHandlers!=null) {
            for(EventHandler<LogicEvent> h:_logicHandlers) {
                h.handle(le);
            }
        }
    }
}
