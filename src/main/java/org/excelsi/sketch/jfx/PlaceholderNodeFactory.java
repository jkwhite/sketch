package org.excelsi.sketch.jfx;


import com.jme3.scene.Node;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Box;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;


public class PlaceholderNodeFactory implements NodeFactory {
    private final AssetManager _assets;


    public PlaceholderNodeFactory(final AssetManager assets) {
        _assets = assets;
    }

    @Override public Spatial createNode(final String name, final Object s) {
        Box n = new Box(Vector3f.ZERO, 1, 1, 1);
        Geometry g = new Geometry("n", n);
        Material mat = new Material(_assets, "Common/MatDefs/Misc/Unshaded.j3md");
        g.setMaterial(mat);
        g.setName(name);
        return g;
    }
}
