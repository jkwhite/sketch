package org.excelsi.sketch.jfx;


import org.excelsi.matrix.MSpace;
import org.excelsi.matrix.MatrixMSpace;
import org.excelsi.sketch.Stage;
import org.excelsi.sketch.ChangeEvent;
import org.excelsi.aether.NHSpace;

import com.jme3.scene.Node;
import com.jme3.scene.LightNode;
import com.jme3.scene.Spatial;


public class LevelController implements Controller<Stage> {
    private static final String PREFIX = "level-";


    @Override public void added(final SceneContext c, final Stage l) {
    }

    @Override public void removed(final SceneContext c, final Stage l) {
    }

    @Override public void changed(final SceneContext c, final ChangeEvent<Stage> e) {
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

            for(final MSpace m:e.getTo().getMatrix().spaces()) {
                final MatrixMSpace mms = (MatrixMSpace) m;
                if(mms!=null) {
                    createSpace(c, n, mms);
                }
            }
        }
    }

    private Spatial createSpace(final SceneContext c, final Node n, final MatrixMSpace mms) {
        final Spatial ms = c.getNodeFactory().createNode("x", mms);
        Spaces.translate(mms, ms);
        n.attachChild(ms);
        if(mms.getOccupant()!=null) {
            final Spatial bot = c.getNodeFactory().createNode(mms.getOccupant().getId(), mms.getOccupant());
            Spaces.translate(mms, bot);
            n.attachChild(bot);
            c.addNode(bot);
            if(((NHSpace)mms).getOccupant().isPlayer()) {
                attachPatsy(n, c, bot);
            }
        }
        return ms;
    }

    private void attachPatsy(final Node parent, final SceneContext c, final Spatial patsy) {
        c.<CloseView>getNode(View.NODE_CAMERA).setPlayer(patsy);
        if(patsy instanceof LightNode) {
            parent.addLight(((LightNode)patsy).getLight());;
        }
    }
}
