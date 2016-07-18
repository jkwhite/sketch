package org.excelsi.sketch.jfx;


import org.excelsi.sketch.Narrative;

import javafx.scene.Group;
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


public class JfxNarrative extends Parent /*implements Narrative*/ {
    private final EventBus _e;


    public JfxNarrative(final EventBus eb) {
        _e = eb;
        addEventHandler(LogicEvent.TYPE, (le)->{
            System.err.println("narrative got event: "+le);
            final Event e = le.e();
            if(e instanceof SelectEvent) {
                select((SelectEvent)e);
            }
        });
    }

    //@Override public void act(Menu<E> m) {
    //}
//
    //@Override public <E> E select(Menu<E> m) {
        //final JfxMenu menu = new JfxMenu(m);
        //getChildren().add(menu);
    //}
    private void select(SelectEvent e) {
        final JfxMenu menu = new JfxMenu(e.getMenu());
        getChildren().add(menu);
    }
}
