package org.excelsi.sketch.jfx;


import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;

import org.excelsi.sketch.ChangeEvent;
import org.excelsi.sketch.Level;


public class JfxWorld extends HudNode {
    public JfxWorld() {
        addLogicHandler((le)->{
            if(le.e() instanceof ChangeEvent) {
                change((ChangeEvent)le.e());
            }
        });
    }

    private void change(final ChangeEvent e) {
        if(e.getTo() instanceof Level) {
            level((Level)e.getTo());
        }
    }

    private void level(final Level level) {
    }
}
