package org.excelsi.sketch.jfx;


import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Group;

import org.excelsi.sketch.Event;
import org.excelsi.sketch.TitleEvent;


public class JfxTitle extends HudNode {
    public JfxTitle() {
        super("title");
        addLogicHandler((le)->{
            final Event e = le.e();
            if(e instanceof TitleEvent) {
                final TitleEvent te = (TitleEvent) e;
                if(!getChildren().isEmpty()) {
                    getChildren().remove(0);
                }
                final Label t = new Label(te.getTitle());
                t.getStyleClass().add("title");
                getChildren().add(t);
            }
        });
    }
}
