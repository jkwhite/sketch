package org.excelsi.sketch.jfx;


import org.excelsi.sketch.Action;
import org.excelsi.sketch.AbstractAction;


public abstract class UIAction extends AbstractAction {
    abstract public void perform(SceneContext c);
}
