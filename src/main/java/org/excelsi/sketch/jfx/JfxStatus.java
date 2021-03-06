package org.excelsi.sketch.jfx;


import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Group;

import org.excelsi.sketch.ChangeEvent;
import org.excelsi.sketch.Stage;


public class JfxStatus extends HudNode {
    public JfxStatus() {
        super("status");
        addLogicHandler((le)->{
            if(le.e() instanceof ChangeEvent) {
                final ChangeEvent e = (ChangeEvent) le.e();
                if(e.getTo() instanceof Stage) {
                    final Stage level = (Stage) e.getTo();
                    if(!getChildren().isEmpty()) {
                        getChildren().remove(0);
                    }
                    final Label t = new Label(String.format("%s, Lv %d", level.getName(), level.getOrdinal()));
                    t.getStyleClass().add("status");
                    getChildren().add(t);
                }
            }
        });
    }
}
