package org.excelsi.sketch.jfx;


import com.jme3.scene.Node;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Box;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.export.binary.BinaryImporter;

import org.excelsi.aether.NHSpace;
import org.excelsi.matrix.Typed;
import java.io.File;


public abstract class AssetNodeFactory<E extends Typed> implements NodeFactory<E> {
    private final AssetManager _assets;


    public AssetNodeFactory(final AssetManager assets) {
        _assets = assets;
    }

    protected final AssetManager assets() {
        return _assets;
    }
}

