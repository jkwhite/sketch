package org.excelsi.sketch;


import org.excelsi.matrix.Bot;
import org.excelsi.matrix.Direction;


public class ContainerEvent extends Event {
    public enum Type { Added, Removed, Changed };

    private final Type _type;
    private final Item _item;
    private final int _idx;
    private final boolean _inc;


    public ContainerEvent(Type t, NHSpace source, Item i, int idx, boolean incremented) {
        super(source);
        _type = t;
        _item = i;
        _idx = idx;
        _inc = incremented;
    }
}
