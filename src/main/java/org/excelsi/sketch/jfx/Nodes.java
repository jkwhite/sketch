package org.excelsi.sketch.jfx;


import com.jme3.scene.Node;
import com.jme3.scene.Spatial;


public class Nodes {
    public static void detachFromParent(final Spatial n) {
        if(n.getParent()!=null) {
            n.getParent().detachChild(n);
        }
    }

    private Nodes() {}
}
