package org.excelsi.sketch.jfx;


import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import org.excelsi.sketch.SelectEvent;
import org.excelsi.sketch.QueryEvent;


public class JfxSelector extends HudNode {
    public JfxSelector() {
        addLogicHandler((le)->{
            if(le.e() instanceof SelectEvent) {
                final JfxMenu menu = new JfxMenu(le.e(), ((SelectEvent)le.e()).getMenu());
                getChildren().add(menu);
                transition(menu, null);
                le.consume();
            }
            else if(le.e() instanceof QueryEvent) {
                final JfxQuery q = new JfxQuery((QueryEvent)le.e());
                getChildren().add(q);
                transition(q, null);
                le.consume();
            }
        });
    }
}
