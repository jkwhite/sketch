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

import org.excelsi.sketch.Menu;
import org.excelsi.sketch.MenuItem;


public class JfxMenu extends Parent {
    public JfxMenu(final Object notify, final Menu m) {
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
                    m.getF().apply(item);
                    m.setChoice(item);
                    synchronized(notify) {
                        notify.notify();
                    }
                }
            });
            menu.getChildren().add(line);
            menu.getStyleClass().add("menu");
        }
        getChildren().add(menu);
    }
}
