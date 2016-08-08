package org.excelsi.sketch.jfx;


import org.excelsi.matrix.MSpace;
import org.excelsi.matrix.MatrixMSpace;
import com.jme3.scene.Spatial;
import com.jme3.math.Vector3f;


public class Spaces {
    public static final Vector3f translation(final MSpace ms) {
        final MatrixMSpace mms = (MatrixMSpace) ms;
        return new Vector3f(UIConstants.HORIZ_RATIO*mms.getI(), 0.0f, UIConstants.VERT_RATIO*mms.getJ());
    }

    public static final Spatial translate(final MSpace ms, final Spatial s) {
        final MatrixMSpace mms = (MatrixMSpace) ms;
        s.setLocalTranslation(UIConstants.HORIZ_RATIO*mms.getI(), 0.0f, UIConstants.VERT_RATIO*mms.getJ());
        return s;
    }

    private Spaces() {}
}
