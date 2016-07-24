package org.excelsi.sketch.jfx;


import java.net.URL;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;

import org.excelsi.sketch.StateChangeEvent;
import java.util.ResourceBundle;

import org.lwjgl.opengl.Display;


public class JfxState extends HudNode {
    private final FXMLLoader _loader = new FXMLLoader();


    public JfxState() {
        ResourceBundle rb = new ListResource(new Object[][]{
            {"screen_width", Integer.toString(Display.getWidth())},
            {"screen_height", Integer.toString(Display.getHeight())}
        });
        addLogicHandler((le)->{
            if(le.e() instanceof StateChangeEvent) {
                final StateChangeEvent se = (StateChangeEvent) le.e();
                if(!getChildren().isEmpty()) {
                    getChildren().remove(0);
                }
                final String urlName = String.format("/org/excelsi/sketch/state-%s.fxml", se.getNewValue().getClass().getSimpleName().toLowerCase());
                final URL url = getClass().getResource(urlName);
                if(url!=null) {
                    try {
                        final Node stateRoot = _loader.load(url, rb);
                        getChildren().add(stateRoot);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
