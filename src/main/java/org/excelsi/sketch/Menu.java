package org.excelsi.sketch;


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


public class Menu<E> extends Parent {
    public Menu(UnaryOperator<MenuItem<E>> f, MenuItem<E>... items) {
        VBox menu = new VBox();
        for(MenuItem item:items) {
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
                    f.apply(item);
                }
            });
            menu.getChildren().add(line);
            menu.getStyleClass().add("menu");
        }
        getChildren().add(menu);
    }
}
