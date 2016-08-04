package org.excelsi.sketch.jfx;


import java.util.Map;
import java.util.HashMap;

import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.excelsi.sketch.KeyEvent;
import org.excelsi.sketch.Action;
import org.excelsi.sketch.ActionEvent;
import org.excelsi.sketch.EventBus;
import org.excelsi.sketch.AbstractAction;
import org.excelsi.sketch.Context;
import org.excelsi.sketch.Quit;


public class JfxWorld extends HudNode {
    private static final Logger LOG = LoggerFactory.getLogger(JfxWorld.class);
    private final Map<Keymap,Action> _actions;


    public JfxWorld() {
        _actions = new HashMap<>();
        _actions.put(new Keymap("t"), new DebugAction());
        _actions.put(new Keymap("q"), new QuitAction());
        LOG.debug("actionmap: "+_actions);
        addLogicHandler((le)->{
            if(le.e() instanceof KeyEvent) {
                if(onKey((KeyEvent)le.e())) {
                    le.consume();
                }
            }
        });
    }

    private boolean onKey(final KeyEvent e) {
        final Keymap m = new Keymap(e.key());
        final Action a = _actions.get(m);
        LOG.debug("found action: "+a+" for key: "+e);
        if(a!=null) {
            EventBus.instance().post("actions", new ActionEvent(this, a));
            return true;
        }
        return false;
    }

    private static class DebugAction extends AbstractAction {
        @Override public void perform() {
        }

        @Override public void perform(final Context c) {
            System.err.println("____ DEBUG ACTION START ____");
            Thread.dumpStack();
            System.err.println("_____ DEBUG ACTION END _____");
        }
    }

    private static class QuitAction extends AbstractAction {
        @Override public void perform(final Context c) {
            if(c.n().confirm("Really quit?")) {
                c.state(new Quit());
            }
        }

        @Override public void perform() {
            System.err.println("____ QUIT ACTION START ____");
            Thread.dumpStack();
            System.err.println("_____ QUIT ACTION END _____");
        }
    }
}
