package org.excelsi.sketch;


import java.util.function.Function;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;

import com.jme3x.jfx.GuiManager;
import com.jme3x.jfx.cursor.proton.ProtonCursorProvider;
import com.jme3x.jfx.FXMLHud;
import com.jme3x.jfx.window.FXMLWindow;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.FontSmoothingType;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import org.lwjgl.opengl.Display;


public class FGui extends SimpleApplication {
    public static void main(String[] args){
        FGui app = new FGui();
        app.setPauseOnLostFocus(false);
        app.start();
    }

    public void simpleInitApp() {
        assetManager.registerLocator("/", ClasspathLocator.class);

        /*
        Box b = new Box(Vector3f.ZERO, 1, 1, 1);
        Geometry geom = new Geometry("Box", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", assetManager.loadTexture("org/excelsi/sketch/title-image3.png"));
        geom.setMaterial(mat);
        */
        //rootNode.attachChild(geom);


        //System.setProperty("javafx.animation.fullspeed", "false");
        //System.setProperty("prism.vsync", "true");
        //System.setProperty("prism.order", "sw");
        //final GuiManager testguiManager = new GuiManager(this.guiNode, this.assetManager, this, true, new ProtonCursorProvider(this, this.assetManager, this.inputManager), new Material(assetManager, "Common/MatDefs/Gui/Gui.j3md"), false);
        final GuiManager testguiManager = new GuiManager(this.guiNode, this.assetManager, this, true, new ProtonCursorProvider(this, this.assetManager, this.inputManager));
        testguiManager.setEverListeningRawInputListener(new RawInputListener() {
            public void beginInput() {
            }

            public void endInput() {
            }

            public void onJoyAxisEvent(JoyAxisEvent e) {
            }

            public void onJoyButtonEvent(JoyButtonEvent e) {
            }

            public void onKeyEvent(KeyInputEvent e) {
                System.err.println(String.format("key char %s for code %d, string %s", e.getKeyChar(), e.getKeyCode(), e.toString()));
            }

            public void onMouseButtonEvent(MouseButtonEvent e) {
            }

            public void onMouseMotionEvent(MouseMotionEvent e) {
            }

            public void onTouchEvent(TouchEvent e) {
            }
        });
        /**
         * 2d gui, use the default input provider
         */
        this.inputManager.addRawInputListener(testguiManager.getInputRedirector());
        try {
            Thread.sleep(500);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        // 256 x 256
        ImageView tlogo = new ImageView(new Image("/org/excelsi/sketch/title-image3.png"));
        // 473 × 116
        ImageView ttitle = new ImageView(new Image("/org/excelsi/sketch/title-text4.png"));

        final VBox title = new VBox();
        title.setSpacing(20);
        title.getChildren().add(tlogo);
        title.getChildren().add(ttitle);
        /*
        VBox menu = new VBox();
        String[][] items = new String[][]{
            {"n", "New game"},
            {"l", "Load game"},
            {"h", "High scores"},
            {"q", "Quit"}
        };
        for(String[] item:items) {
            Label key = new Label(item[0]+" - ");
            key.getStyleClass().add("key");
            Label desc = new Label(item[1]);
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
            menu.getChildren().add(line);
            menu.getStyleClass().add("menu");
        }
        */
        Menu menu = new Menu<Runnable>(
            (item) -> { item.item().run(); return null; },
            new MenuItem<Runnable>("n", "New game", null),
            new MenuItem<Runnable>("l", "Load game", null),
            new MenuItem<Runnable>("h", "High scores", null),
            new MenuItem<Runnable>("q", "Quit", () -> { System.exit(0); })
        );
        title.setAlignment(Pos.CENTER);
        //tlogo.setAlignment(Pos.CENTER);
        //ttitle.setAlignment(Pos.CENTER);
        //HBox m2 = new HBox();
        //m2.getChildren().add(menu);
        //title.getChildren().add(m2);
        title.setTranslateX(Display.getWidth()/2-473/2);
        title.setTranslateY(20);
        //menu.setAlignment(Pos.CENTER);
        menu.setTranslateY(Display.getHeight()/2+60);
        menu.setTranslateX(Display.getWidth()/2-150);
        //Label[] labels = new Label[]{
            //new Label("n - New game"),
            //new Label("l - Load game"),
            //new Label("h - High scores"),
            //new Label("q - Quit")
        //};
        //for(Label label:labels) {
            //label.setFont(new Font("Helvetica Neue Condensed Bold", 68));
            //label.setStyle("-fx-font-smoothing-type: gray;");
            //label.setTextFill(Color.WHITE);
            //menu.getChildren().add(label);
        //}
        //Text t = new Text("EXPLORATIONS IN CELLULAR SPACES");
        //t.setFont(new Font("Helvetica Neue Condensed Bold", 68));
        //t.setFill(Color.WHITE);
        //t.setSmooth(true);
        //t.setFontSmoothingType(FontSmoothingType.LCD);
        //TextField f = new TextField("EXPLORATIONS IN CELLULAR SPACES");
        //f.setFont(new Font("Helvetica Neue Condensed Bold", 68));
        //f.setEditable(false);
        //f.setFill(Color.WHITE);

        //menu.getChildren().add(t);
        //menu.getChildren().add(f);

        Platform.runLater(new Runnable() {
            public void run() {
                Scene scene = testguiManager.getRootGroup().getScene();
                scene.getStylesheets().add("/org/excelsi/sketch/tower-hud.css");

                //Group hud = new Group();
                //hud.getChildren().add(menu);
                //SubScene sub = new SubScene(hud, scene.getWidth(), scene.getHeight(), false, SceneAntialiasing.BALANCED);
                //sub.setUserAgentStylesheet("/org/excelsi/sketch/tower-hud.css");
                //testguiManager.getRootGroup().getChildren().add(sub);
                testguiManager.getRootGroup().getChildren().add(title);
                testguiManager.getRootGroup().getChildren().add(menu);
            }
        });

        /*
        final FXMLHud testhud = new FXMLHud("org/excelsi/sketch/loading_screen.fxml");
        //final FXMLHud testhud = new FXMLHud("org/excelsi/sketch/tower-title.fxml");
        testhud.precache();
        testguiManager.attachHudAsync(testhud);

        //final FXMLWindow testwindow = new FXMLWindow("org/excelsi/sketch/loading_screen.fxml");
        final FXMLWindow testwindow = new FXMLWindow("org/excelsi/sketch/tower-title.fxml");
        testwindow.externalized().set(true);
        testwindow.precache();
        testwindow.titleProperty().set("TestTitle");
        testguiManager.attachHudAsync(testwindow);
        */

        flyCam.setEnabled(false);
        inputManager.setCursorVisible(true);
        mouseInput.setCursorVisible(true);
    }
}
