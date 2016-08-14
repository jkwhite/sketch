package org.excelsi.sketch.jfx;


import com.jme3.scene.Node;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Box;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.export.binary.BinaryImporter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.excelsi.aether.NHSpace;
import org.excelsi.aether.Rand;
import java.io.File;


public class SpaceNodeFactory extends AssetNodeFactory<NHSpace> {
    private static final Logger LOG = LoggerFactory.getLogger(SpaceNodeFactory.class);


    public SpaceNodeFactory(final AssetManager assets) {
        super(assets);
    }

    @Override public Spatial createNode(final String name, final NHSpace s) {
        try {
            final String model = String.format("/%s_%d_%d.blend", Spaces.format(s.getModel()), 6, 0);
            final Spatial n = assets().loadModel(model);
            if(true) {
                Material mat = new Material(assets(), "Common/MatDefs/Light/Lighting.j3md");
                mat.setFloat("Shininess", 32f);
                mat.setBoolean("UseMaterialColors", true);
                mat.setColor("Ambient",  ColorRGBA.Black);
                mat.setColor("Diffuse",  ColorRGBA.Gray);
                mat.setColor("Specular", ColorRGBA.White);
                n.setMaterial(mat);
                final Node sp = new Node();
                for(int i=0;i<4;i++) {
                    final Spatial cl = n.clone();
                    //cl.setLocalScale(0.5f);
                    cl.setLocalTranslation(UIConstants.HORIZ_RATIO*Rand.om.nextFloat(), 0f, UIConstants.VERT_RATIO*Rand.om.nextFloat());
                    sp.attachChild(cl);
                }
                return sp;
            }
            else {
                //final Spatial n = assets().loadModel("/box1.blend");
                n.setLocalScale(2.0f);
                LOG.debug("loaded spatial "+n);
                //Material mat = new Material(assets(), "Common/MatDefs/Misc/Unshaded.j3md");
                Material mat = new Material(assets(), "Common/MatDefs/Light/Lighting.j3md");
                mat.setFloat("Shininess", 32f);
                mat.setBoolean("UseMaterialColors", true);
                mat.setColor("Ambient",  ColorRGBA.Black);
                mat.setColor("Diffuse",  ColorRGBA.Gray);
                mat.setColor("Specular", ColorRGBA.White);
                n.setMaterial(mat);
                return n;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new Node("blot");
    }
}
