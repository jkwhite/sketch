package org.excelsi.sketch.jfx;


import com.jme3.scene.Spatial;


public interface NodeFactory<E> {
    Spatial createNode(String name, E s);
}
