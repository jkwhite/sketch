package org.excelsi.sketch.jfx;


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

import org.excelsi.sketch.Event;
import org.excelsi.sketch.EventBus;
import org.excelsi.sketch.Menu;
import org.excelsi.sketch.MenuItem;
import org.excelsi.sketch.SelectEvent;
import org.excelsi.sketch.MessageEvent;
import org.excelsi.sketch.KeyEvent;


public class JfxNarrative extends Group /*implements Narrative*/ {
    private final EventBus _e;


    public JfxNarrative(final EventBus eb) {
        _e = eb;
        addEventHandler(LogicEvent.TYPE, (le)->{
            System.err.println("narrative got event: "+le);
            final Event e = le.e();
            if(e instanceof SelectEvent) {
                select((SelectEvent)e);
                le.consume();
            }
            else if(e instanceof MessageEvent) {
                message((MessageEvent)e);
                le.consume();
            }
            else if(e instanceof KeyEvent) {
                for(Node child:getChildren()) {
                    if(child instanceof HudNode) {
                        ((HudNode)child).onEvent(le);
                        if(le.isConsumed()) {
                            break;
                        }
                    }
                }
            }
        });
    }

    private void message(MessageEvent e) {
        Label t = new Label(e.getMessage());
        t.getStyleClass().add("message");
        getChildren().add(t);
        System.err.println("added label "+t);
    }

    private void select(SelectEvent e) {
        final JfxMenu menu = new JfxMenu(e, e.getMenu());
        getChildren().add(menu);
    }
}
