package org.excelsi.sketch.jfx;


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


public class JmeEventHandler implements EventBus.Handler {
    private final Node _root;
    private final AssetManager _assets;


    public JmeEventHandler(final AssetManager assets, final Node root) {
        _assets = assets;
        _root = root;
    }

    @Override public void handleEvent(final Event e) {
        System.err.println("jme got event: "+e);
        if(e instanceof ChangeEvent) {
            change((ChangeEvent)e);
        }
    }

    private void change(final ChangeEvent e) {
        if(e.getTo() instanceof Level) {
            level((Level)e.getTo());
        }
    }

    private void level(final Level level) {
        //Sphere s = new Sphere(10, 10, 10);
        Box s = new Box(Vector3f.ZERO, 1, 1, 1);
        Geometry g = new Geometry("s", s);
        Material mat = new Material(_assets, "Common/MatDefs/Misc/Unshaded.j3md");
        g.setMaterial(mat);
        _root.attachChild(g);
        System.err.println("attached sphere");
    }
}
