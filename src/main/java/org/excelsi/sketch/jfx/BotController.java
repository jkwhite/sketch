package org.excelsi.sketch.jfx;


import org.excelsi.matrix.MSpace;
import org.excelsi.matrix.MatrixMSpace;
import org.excelsi.aether.NHBot;
import org.excelsi.sketch.ChangeEvent;
import org.excelsi.sketch.MoveEvent;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;


public class BotController implements Controller<MSpace> {
    public static final int MULTIPLIER = 2;

    private static final String ROOT = "root";
    private static final String PREFIX = "level-";


    @Override public void added(final SceneContext c, final MSpace b) {
    }

    @Override public void removed(final SceneContext c, final MSpace b) {
    }

    @Override public void changed(final SceneContext c, final ChangeEvent<MSpace> e) {
        if(e instanceof MoveEvent) {
            final MoveEvent me = (MoveEvent) e;
            final NHBot b = (NHBot) me.getBot();
            final Spatial s = c.getSpatial(me.getBot().getId());
            final MatrixMSpace mms = (MatrixMSpace) me.getBot().getEnvironment().getSpace();
            s.setLocalTranslation(MULTIPLIER*mms.getI(), 0.5f, MULTIPLIER*mms.getJ());
            if(b.isPlayer()) {
                updateView(c, mms);
            }
        }
    }

    private void updateView(final SceneContext c, final MatrixMSpace m) {
        final CloseView v = (CloseView) c.getNode("camera");
        v.center(getTranslation(m));
    }

    private Vector3f getTranslation(MatrixMSpace m) {
        return new Vector3f(MULTIPLIER*m.getI(), 0.5f, MULTIPLIER*m.getJ());
    }
}
