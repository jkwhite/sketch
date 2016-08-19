package org.excelsi.sketch.jfx;


import org.excelsi.sketch.AddEvent;
import org.excelsi.sketch.ChangeEvent;


public interface Controller<E> {
    void added(SceneContext c, AddEvent<E> e);
    void removed(SceneContext c, E e);
    void changed(SceneContext c, ChangeEvent<E> e);
}
