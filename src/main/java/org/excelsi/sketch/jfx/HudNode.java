package org.excelsi.sketch.jfx;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public abstract class HudNode extends Group implements Hud {
    private String _transition;
    private List<EventHandler<LogicEvent>> _logicHandlers;


    public HudNode() {
    }

    public HudNode(final String style) {
        //getStyleClass().add(style);
    }

    public final void addLogicHandler(final EventHandler<LogicEvent> h) {
        if(_logicHandlers==null) {
            _logicHandlers = new ArrayList<>(1);
        }
        _logicHandlers.add(h);
    }

    @Override public final void onEvent(LogicEvent le) {
        if(_logicHandlers!=null) {
            for(EventHandler<LogicEvent> h:_logicHandlers) {
                h.handle(le);
            }
        }
    }

    public void setTransition(final String t) {
        _transition = t;
    }

    public String getTransition() {
        return _transition;
    }

    protected void transition(final Region n, final EventHandler<ActionEvent> onFinished) {
        if(null!=_transition) {
            Transitions.transition(_transition, n, onFinished);
        }
        else if(null!=onFinished) {
            onFinished.handle(null);
        }
    }
}
