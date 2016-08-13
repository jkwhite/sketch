package org.excelsi.sketch.jfx;


import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.math.Spline.SplineType;


public class Animations {
    public static void move(final Spatial s, final Vector3f from, final Vector3f to) {
        final MotionPath p = new MotionPath();
        p.addWayPoint(from);
        p.addWayPoint(to);
        final MotionEvent e = new MotionEvent(s, p, 0.25f);
        e.play();
    }

    private Animations() {}
}
