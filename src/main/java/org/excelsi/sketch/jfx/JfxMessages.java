package org.excelsi.sketch.jfx;


import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;

import org.excelsi.sketch.MessageEvent;


public class JfxMessages extends HudNode {
    public JfxMessages() {
        addLogicHandler((le)->{
            if(le.e() instanceof MessageEvent) {
                final MessageEvent e = (MessageEvent) le.e();
                final Node t;
                if(e.getMessage().length()<80) {
                    t = new Label(e.getMessage());
                }
                else {
                    BorderPane bt = new BorderPane();
                    bt.setCenter(new Text(e.getMessage()));
                    t = bt;
                }
                t.getStyleClass().add("message");
                t.getStyleClass().add(e.getType().toString());
                getChildren().add(t);
                switch(e.getType()) {
                    case ephemeral:
                        final FadeTransition ft = new FadeTransition(Duration.millis(3000), t);
                        ft.setFromValue(1.0);
                        ft.setToValue(0.0);
                        ft.play();
                        break;
                    case permanent:
                    default:
                        break;
                }
            }
        });
    }
}
