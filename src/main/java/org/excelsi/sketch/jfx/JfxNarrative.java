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


import org.excelsi.sketch.Event;
import org.excelsi.sketch.EventBus;
import org.excelsi.sketch.Menu;
import org.excelsi.sketch.MenuItem;
import org.excelsi.sketch.SelectEvent;
import org.excelsi.sketch.MessageEvent;
import org.excelsi.sketch.KeyEvent;
import org.excelsi.sketch.PauseEvent;


public class JfxNarrative extends Group {
    public JfxNarrative() {
        addEventHandler(LogicEvent.TYPE, (le)->{
            System.err.println("narrative got event: "+le);
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
        System.err.println("sending event "+le+" to: "+getChildren());
        while(!frontier.isEmpty()) {
            final Node child = frontier.remove(0);
            if(child instanceof Parent) {
                frontier.addAll(((Parent)child).getChildrenUnmodifiable());
            }
            if(child instanceof Hud) {
                System.err.println("sending event to: "+child);
                ((Hud)child).onEvent(le);
                if(le.isConsumed()) {
                    System.err.println("consumed");
                    break;
                }
            }
        }
    }

    //private void select(SelectEvent e) {
        //final JfxMenu menu = new JfxMenu(e, e.getMenu());
        //getChildren().add(menu);
        //System.err.println("added menu");
    //}

    private void pause(PauseEvent e) {
        final JfxPause m = new JfxPause("-- More --", e);
        getChildren().add(0, m);
    }
}
