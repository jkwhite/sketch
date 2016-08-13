package org.excelsi.sketch.jfx;


import org.excelsi.matrix.MSpace;
import org.excelsi.matrix.MatrixMSpace;
import org.excelsi.sketch.Stage;
import org.excelsi.sketch.ChangeEvent;
import org.excelsi.aether.NHSpace;

import com.jme3.scene.Node;
import com.jme3.scene.LightNode;
import com.jme3.light.Light;
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
            final Node lev = (Node) c.getNodeFactory().createNode(PREFIX+e.getTo().getOrdinal(), e.getTo());
            c.getRoot().attachChild(lev);
            c.addNode(lev);

            for(final MSpace m:e.getTo().getMatrix().spaces()) {
                final MatrixMSpace mms = (MatrixMSpace) m;
                if(mms!=null) {
                    createSpace(c, lev, mms);
                }
            }
        }
    }

    private Spatial createSpace(final SceneContext c, final Node lev, final MatrixMSpace mms) {
        final Spatial ms = c.getNodeFactory().createNode("x", mms);
        Spaces.translate(mms, ms);
        lev.attachChild(ms);
        if(mms.getOccupant()!=null) {
            final Spatial bot = c.getNodeFactory().createNode(mms.getOccupant().getId(), mms.getOccupant());
            Spaces.translate(mms, bot);
            lev.attachChild(bot);
            c.addNode(bot);
            if(((NHSpace)mms).getOccupant().isPlayer()) {
                attachPatsy(lev, c, bot);
            }
        }
        return ms;
    }

    private void attachPatsy(final Node parent, final SceneContext c, final Spatial patsy) {
        c.<CloseView>getNode(View.NODE_CAMERA).setPlayer(patsy);
        if(patsy instanceof Litten) {
            for(final Light light:((Litten)patsy).getAllLights()) {
                System.err.println("****************  ADDING LIGHT: "+light);
                parent.addLight(light);
            }
            //parent.addLight(((LightNode)patsy).getLight());;
        }
    }
}
