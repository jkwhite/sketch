package org.excelsi.sketch.jfx;


import java.util.HashMap;
import java.util.Map;

import org.excelsi.sketch.Event;
import org.excelsi.sketch.EventBus;
import org.excelsi.sketch.ChangeEvent;
import org.excelsi.sketch.Level;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Box;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;

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


    public JmeEventHandler(final AssetManager assets, final ControllerFactory cfactory, final NodeFactory nfactory, final Node root) {
        _assets = assets;
        _cfactory = cfactory;
        _nfactory = nfactory;
        _root = root;
        _ctx = new SceneContext(_root, _nfactory);
    }

    @Override public void handleEvent(final Event e) {
        LOG.debug("jme got event: {}", e);
        if(e instanceof ChangeEvent) {
            change((ChangeEvent)e);
        }
    }

    private void change(final ChangeEvent e) {
        Controller c = _cfactory.createController(e.getType());
        c.changed(_ctx, e);
        //if(e.getTo() instanceof Level) {
            //level((Level)e.getTo());
        //}
    }

    private void level(final Level level) {
        //Sphere s = new Sphere(10, 10, 10);
        /*
        Box s = new Box(Vector3f.ZERO, 1, 1, 1);
        Geometry g = new Geometry("s", s);
        Material mat = new Material(_assets, "Common/MatDefs/Misc/Unshaded.j3md");
        g.setMaterial(mat);
        _root.attachChild(g);
        System.err.println("attached sphere");
        */
        //Controller c = _controllers.get(level);
        //if(c==null) {
            //c = _cfactory.createController(level);
            //_controllers.put(level, c);
        //}
        //c.handle(level);
    }
}
