package org.excelsi.sketch.jfx;


import com.jme3.scene.Node;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Box;
import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.Vector3f;
import org.excelsi.matrix.Typed;


public class PlaceholderNodeFactory implements NodeFactory {
    private final AssetManager _assets;
    private final BitmapFont _font;


    public PlaceholderNodeFactory(final AssetManager assets) {
        _assets = assets;
        _font = _assets.loadFont("Interface/Fonts/Default.fnt");
    }

    @Override public Spatial createNode(final String name, final Typed s) {
        //Box n = new Box(Vector3f.ZERO, 0.5, 0.5, 0.5);
        //Geometry g = new Geometry("n", n);
        //Material mat = new Material(_assets, "Common/MatDefs/Misc/Unshaded.j3md");
        //g.setMaterial(mat);
        BitmapText g = new BitmapText(_font);
        g.setText(name+": "+s.toString());
        g.setSize(0.1f);
        g.setName(name);
        return g;
    }
}
