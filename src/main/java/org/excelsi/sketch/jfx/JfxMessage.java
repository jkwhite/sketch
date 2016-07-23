package org.excelsi.sketch.jfx;


import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Group;


public class JfxMessage extends HudNode {
    public JfxMessage(final String m, final Object notify) {
        addLogicHandler((le)->{
            System.err.println("message got event:: "+le);
            le.consume();
            ((Group)getParent()).getChildren().remove(this);
            synchronized(notify) {
                notify.notify();
            }
        });
        getChildren().add(new Label(m));
    }
}
