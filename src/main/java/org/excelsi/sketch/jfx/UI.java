package org.excelsi.sketch.jfx;


import java.util.HashMap;
import java.util.Map;

import org.excelsi.sketch.Level;
import com.jme3.asset.AssetManager;


public class UI {
    public static NodeFactory nodeFactory(final AssetManager assets) {
        final Map<String,NodeFactory> nfs = new HashMap<>();
        nfs.put("level", new LevelNodeFactory());
        nfs.put("space", new SpaceNodeFactory(assets));
        nfs.put("bot", new BotNodeFactory(assets));
        return new CompositeNodeFactory(nfs, new PlaceholderNodeFactory(assets));
    }
}
