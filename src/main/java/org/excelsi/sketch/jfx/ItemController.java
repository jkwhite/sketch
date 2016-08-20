package org.excelsi.sketch.jfx;


import org.excelsi.matrix.MSpace;
import org.excelsi.matrix.MatrixMSpace;
import org.excelsi.sketch.AddEvent;
import org.excelsi.sketch.ChangeEvent;
import org.excelsi.aether.NHSpace;
import org.excelsi.aether.Item;

import com.jme3.scene.Node;
import com.jme3.scene.LightNode;
import com.jme3.light.Light;
import com.jme3.scene.Spatial;


public class ItemController implements Controller<Item> {
    @Override public void added(final SceneContext c, final AddEvent<Item> e) {
        //final Spatial item = c.getNodeFactory().createNode(e.getAdded().getId(), e.getAdded());
        final Spatial item = Spaces.createItem(c, e.getAdded());
        final NHSpace mms = (NHSpace) e.getSource();
        //Spaces.translate(mms, item);
        final Node sp = c.getNode(mms.getId());
        //sp.attachChild(item);
        Spaces.attachItem(sp, item);
    }

    @Override public void removed(final SceneContext c, final Item l) {
    }

    @Override public void changed(final SceneContext c, final ChangeEvent<Item> e) {
    }
}
