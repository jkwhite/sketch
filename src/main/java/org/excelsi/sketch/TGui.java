package org.excelsi.sketch;


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

import tonegod.gui.core.Screen;
import tonegod.gui.controls.windows.Window;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.controls.windows.Panel;
import tonegod.gui.controls.text.LabelElement;
import tonegod.gui.effects.Effect;
import tonegod.gui.framework.animation.Interpolation;



public class TGui extends SimpleApplication {
    public int winCount = 0;
    private Screen screen;
     
    public static void main(String[] args){
        TGui app = new TGui();
        app.setPauseOnLostFocus(false);
        app.start();
    }

    public final void createNewWindow(String someWindowTitle) {
        Window nWin = new Window(
            screen,
            "Window" + winCount,
            new Vector2f( (screen.getWidth()/2)-175, (screen.getHeight()/2)-100 )
        );
        nWin.setWindowTitle(someWindowTitle);
        screen.addElement(nWin);
        winCount++;
    }
     
    public void simpleInitApp() {
        assetManager.registerLocator("/", ClasspathLocator.class);
        screen = new Screen(this, "org/excelsi/sketch/style-parchment.gui.xml");
        screen.initialize();
        guiNode.addControl(screen);
     
        Panel logo = new Panel(
                screen,                                  // Screen
                "logo",                                 // ID
                new Vector2f(screen.getWidth()/2-256/2, 20f),                  // position
                new Vector2f(256, 256),                  // size
                new Vector4f(10f, 10f, 10f, 10f),        // resize borders
                "org/excelsi/sketch/title-image3.png");  // image
        screen.addElement(logo);

        // 473 × 116
        Panel title = new Panel(
                screen,                                  // Screen
                "title",                                 // ID
                new Vector2f(screen.getWidth()/2-473/2, 20+256+20),                  // position
                new Vector2f(473, 116),                  // size
                new Vector4f(10f, 10f, 10f, 10f),        // resize borders
                "org/excelsi/sketch/title-text4.png");  // image
        screen.addElement(title);

        {
            Effect fadeIn = new Effect(Effect.EffectType.FadeIn, Effect.EffectEvent.Show, 2f);
            logo.addEffect(Effect.EffectEvent.Show, fadeIn);
            screen.getEffectManager().applyEffect(fadeIn);
        }

        {
            Effect fadeIn2 = new Effect(Effect.EffectType.FadeIn, Effect.EffectEvent.Show, 4f);
            fadeIn2.setInterpolation(Interpolation.pow3In);
            title.addEffect(Effect.EffectEvent.Show, fadeIn2);
            screen.getEffectManager().applyEffect(fadeIn2);
        }

        //{
            //Effect slideIn = new Effect(Effect.EffectType.SlideIn, Effect.EffectEvent.Show, 2f);
            //slideIn.setEffectDirection(Effect.EffectDirection.Top);
            //slideIn.setInterpolation(Interpolation.pow3Out);
            //logo.addEffect(slideIn);
            //screen.getEffectManager().applyEffect(slideIn);
        //}
//
        //{
            //Effect slideIn2 = new Effect(Effect.EffectType.SlideIn, Effect.EffectEvent.Show, 2f);
            //slideIn2.setEffectDirection(Effect.EffectDirection.Bottom);
            //slideIn2.setInterpolation(Interpolation.pow3Out);
            //title.addEffect(slideIn2);
            //screen.getEffectManager().applyEffect(slideIn2);
        //}


        Panel menu = new Panel(
                screen,                                  // Screen
                "menu",                                 // ID
                new Vector2f(screen.getWidth()/2-200/2, 20+256+116+120),                  // position
                new Vector2f(200, 200),                  // size
                new Vector4f(10f, 10f, 10f, 10f),        // resize borders
                "org/excelsi/sketch/parchment-small2.jpg");  // image
        LabelElement e1 = new LabelElement(screen, "new", new Vector2f(0,0), new Vector2f(200,28));
        e1.setText("n - New game");
        e1.setSizeToText(true);
        LabelElement e2 = new LabelElement(screen, "load", new Vector2f(0,20));
        e2.setText("l - Load game");
        e2.setUseTextClipping(false);
        LabelElement e3 = new LabelElement(screen, "scores", new Vector2f(0,40));
        e3.setText("h - High scores");
        e3.setUseTextClipping(false);
        LabelElement e4 = new LabelElement(screen, "quit", new Vector2f(0,60));
        e4.setText("q - Quit");
        e3.setUseTextClipping(false);
        menu.addChild(e1);
        menu.addChild(e2);
        menu.addChild(e3);
        menu.addChild(e4);
        menu.addEffect(Effect.EffectEvent.Show, new Effect(Effect.EffectType.FadeIn, Effect.EffectEvent.Show, 10f));
        //screen.addElement(menu);

        // Add window
        Window win = new Window(screen, "win", new Vector2f(0, 0));
     
        // create button and add to window
        ButtonAdapter makeWindow = new ButtonAdapter( screen, "Btn1", new Vector2f(15, 55) ) {
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                createNewWindow("New Window " + winCount);
            }
        };
     
        // Add it to our initial window
        win.addChild(makeWindow);
        //title.showWithEffect();
     
        // Add window to the screen
        //screen.addElement(win);
        flyCam.setEnabled(false);
        inputManager.setCursorVisible(true);
        mouseInput.setCursorVisible(true);
        //screen.releaseForcedCursor();
    }
}
