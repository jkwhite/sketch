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
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.excelsi.aether.NHSpace;
import java.io.File;


public class SpaceNodeFactory extends AssetNodeFactory<NHSpace> {
    private static final Logger LOG = LoggerFactory.getLogger(SpaceNodeFactory.class);


    public SpaceNodeFactory(final AssetManager assets) {
        super(assets);
    }

    @Override public Spatial createNode(final String name, final NHSpace s) {
        //return new Node(name);
        //BinaryImporter b = new BinaryImporter();
        //final String file = "/Users/jkw/code/tower/models/courier/src/resources/models/courier/hash_0_0.jme";

        try {
            //Object o = b.load(new File(file));
            //System.err.println("**** LOADED: "+o);
            final Spatial n = assets().loadModel("/ampersand_6_0.blend");
            //final Spatial n = assets().loadModel("/box1.blend");
            //n.setLocalScale(0.5f);
            LOG.debug("loaded spatial "+n);
            Material mat = new Material(assets(), "Common/MatDefs/Misc/Unshaded.j3md");
            n.setMaterial(mat);
            return n;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new Node("blot");
    }
}
