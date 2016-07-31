package org.excelsi.sketch.jfx;


import java.util.HashMap;
import java.util.Map;

import org.excelsi.sketch.Level;
import com.jme3.asset.AssetManager;


public class UI {
    public static NodeFactory nodeFactory(final AssetManager assets) {
        final Map<String,NodeFactory> nfs = new HashMap<>();
        nfs.put(Level.class.getName(), new LevelNodeFactory());
        return new CompositeNodeFactory(nfs, new PlaceholderNodeFactory(assets));
    }
}