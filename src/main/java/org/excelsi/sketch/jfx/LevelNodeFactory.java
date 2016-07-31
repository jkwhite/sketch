package org.excelsi.sketch.jfx;


import com.jme3.scene.Node;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Box;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;

import org.excelsi.sketch.Level;


public class LevelNodeFactory implements NodeFactory<Level> {
    @Override public Spatial createNode(final String name, final Level s) {
        return new Node(name);
    }
}
