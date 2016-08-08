package org.excelsi.sketch.jfx;


import java.util.List;
import java.util.LinkedList;
import org.excelsi.sketch.Narrative;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.excelsi.sketch.Event;
import org.excelsi.sketch.EventBus;
import org.excelsi.sketch.Menu;
import org.excelsi.sketch.MenuItem;
import org.excelsi.sketch.SelectEvent;
import org.excelsi.sketch.MessageEvent;
import org.excelsi.sketch.KeyEvent;
import org.excelsi.sketch.PauseEvent;


public class JfxNarrative extends Group {
    private static final Logger LOG = LoggerFactory.getLogger(JfxNarrative.class);


    public JfxNarrative() {
        addEventHandler(LogicEvent.TYPE, (le)->{
            LOG.debug("narrative got event: "+le);
            final Event e = le.e();
            if(e instanceof PauseEvent) {
                pause((PauseEvent)e);
            }
            else {
                onEvent(le);
            }
        });
    }

    private void onEvent(final LogicEvent le) {
        List<Node> frontier = new LinkedList<>();
        frontier.addAll(getChildren());
        while(!frontier.isEmpty()) {
            final Node child = frontier.remove(0);
            if(child instanceof Parent) {
                frontier.addAll(0, ((Parent)child).getChildrenUnmodifiable());
            }
            if(child instanceof Hud) {
                ((Hud)child).onEvent(le);
                if(le.isConsumed()) {
                    break;
                }
            }
        }
    }

    private void pause(PauseEvent e) {
        final JfxPause m = new JfxPause("-- More --", e);
        getChildren().add(0, m);
    }
}
