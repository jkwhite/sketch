package org.excelsi.sketch.jfx;


import org.excelsi.matrix.MSpace;
import org.excelsi.matrix.MatrixMSpace;
import org.excelsi.sketch.Level;
import org.excelsi.sketch.MatrixLevel;
import org.excelsi.sketch.ChangeEvent;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;


public class LevelController implements Controller<Level> {
    public static final int MULTIPLIER = 2;

    private static final String ROOT = "root";
    private static final String PREFIX = "level-";


    @Override public void added(final SceneContext c, final Level l) {
    }

    @Override public void removed(final SceneContext c, final Level l) {
    }

    @Override public void changed(final SceneContext c, final ChangeEvent<Level> e) {
        if(e.getFrom()!=null) {
            Node from = c.getNode(PREFIX+e.getFrom().getOrdinal());
            if(from!=null) {
                Nodes.detachFromParent(from);
            }
        }
        if(e.getTo()!=null) {
            final Node n = (Node) c.getNodeFactory().createNode(PREFIX+e.getTo().getOrdinal(), e.getTo());
            c.getRoot().attachChild(n);
            c.addNode(n);

            for(MSpace m:e.getTo().getMatrix().spaces()) {
                final MatrixMSpace mms = (MatrixMSpace) m;
                if(mms!=null) {
                    final Spatial ms = c.getNodeFactory().createNode("x", mms);
                    ms.setLocalTranslation(MULTIPLIER*mms.getI(), 0, MULTIPLIER*mms.getJ());
                    n.attachChild(ms);
                    if(mms.getOccupant()!=null) {
                        final Spatial bot = c.getNodeFactory().createNode("x", mms.getOccupant());
                        bot.setLocalTranslation(MULTIPLIER*mms.getI(), 0.5f, MULTIPLIER*mms.getJ());
                        n.attachChild(bot);
                    }
                }
            }
        }
    }
}
