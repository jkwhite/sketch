package org.excelsi.sketch.jfx;


import java.util.HashMap;
import java.util.Map;

import org.excelsi.sketch.Event;
import org.excelsi.sketch.EventBus;
import org.excelsi.sketch.ChangeEvent;
import org.excelsi.sketch.ActionEvent;
import org.excelsi.sketch.Level;
import org.excelsi.sketch.AddEvent;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Box;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class JmeEventHandler implements EventBus.Handler {
    private static final Logger LOG = LoggerFactory.getLogger(JfxNarrative.class);
    private final Node _root;
    //private final Map<Object,Controller> _controllers = new HashMap<>();
    private final SceneContext _ctx;
    private final ControllerFactory _cfactory;
    private final NodeFactory _nfactory;
    private final AssetManager _assets;


    public JmeEventHandler(final Camera camera, final AssetManager assets, final ControllerFactory cfactory, final NodeFactory nfactory, final Node root) {
        _assets = assets;
        _cfactory = cfactory;
        _nfactory = nfactory;
        _root = root;
        _ctx = new SceneContext(camera, _root, _nfactory);
        final CloseView v = new CloseView("camera", _root, null, camera);
        v.activate();
        _ctx.addNode(v);
    }

    @Override public void handleEvent(final Event e) {
        LOG.debug("jme got event: {}", e);
        if(e instanceof ChangeEvent) {
            change((ChangeEvent)e);
        }
        else if(e instanceof AddEvent) {
            add((AddEvent)e);
        }
        else if(e instanceof ActionEvent) {
            uiAction((ActionEvent)e);
        }
    }

    private void change(final ChangeEvent e) {
        Controller c = _cfactory.createController(e);
        c.changed(_ctx, e);
    }

    private void add(final AddEvent e) {
        Controller c = _cfactory.createController(e);
        c.added(_ctx, e);
    }

    private void uiAction(final ActionEvent e) {
        final UIAction a = (UIAction) e.getAction();
        a.perform(_ctx);
    }
}
