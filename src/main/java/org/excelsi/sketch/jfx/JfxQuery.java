package org.excelsi.sketch.jfx;


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

import java.util.function.Function;
import java.util.function.UnaryOperator;

import org.excelsi.sketch.Event;
import org.excelsi.sketch.QueryEvent;
import org.excelsi.sketch.KeyEvent;


public class JfxQuery extends HudRegion {
    public JfxQuery(final QueryEvent e) {
        getStyleClass().add("query");
        addLogicHandler((le)->{
            le.consume();
            final Event ke = le.e();
            if(ke instanceof KeyEvent) {
                switch(((KeyEvent)ke).key()) {
                    case "y":
                        choose(e, true);
                        break;
                    case "n":
                        choose(e, false);
                        break;
                }
            }
        });
        final Label msg = new Label(e.getMessage()+" (y/n) ");
        getChildren().add(msg);
        System.err.println("*AAAAAAAAADDED MESSAGE: "+msg);
        /*
        final VBox menu = new VBox();
        for(MenuItem item:m.getItems()) {
            Label key = new Label(item.key()+" - ");
            key.getStyleClass().add("key");
            Label desc = new Label(item.description());
            desc.getStyleClass().add("keydesc");
            HBox line = new HBox();
            line.getChildren().add(key);
            line.getChildren().add(desc);
            line.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    line.setEffect(new Glow());
                }
            });
            line.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    line.setEffect(null);
                }
            });
            line.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    choose(m, item, notify);
                }
            });
            //menu.getChildren().add(line);
            //menu.getStyleClass().add("menu");
        }
        getChildren().add(menu);
        */
    }

    private void choose(final QueryEvent e, final Object choice) {
        ((Group)getParent()).getChildren().remove(this);
        e.setAnswer(choice);
        synchronized(e) {
            e.notify();
        }
    }
}
