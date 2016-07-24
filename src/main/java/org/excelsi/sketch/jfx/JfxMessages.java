package org.excelsi.sketch.jfx;


import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import org.excelsi.sketch.MessageEvent;


public class JfxMessages extends HudNode {
    public JfxMessages() {
        addLogicHandler((le)->{
            if(le.e() instanceof MessageEvent) {
                final MessageEvent e = (MessageEvent) le.e();
                final Label t = new Label(e.getMessage());
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
