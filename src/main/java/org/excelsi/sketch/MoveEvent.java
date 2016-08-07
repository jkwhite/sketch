package org.excelsi.sketch;


import org.excelsi.matrix.MSpace;
import org.excelsi.matrix.Bot;


public class MoveEvent extends ChangeEvent<MSpace> {
    private final Bot _b;
    private final MSpace _from;
    private final MSpace _to;


    public MoveEvent(Object source, Bot b, MSpace from, MSpace to) {
        super(source, "bot", from, to);
        _b = b;
        _from = from;
        _to = to;
    }

    public Bot getBot() {
        return _b;
    }

    public MSpace getFrom() {
        return _from;
    }

    public MSpace getTo() {
        return _to;
    }
}
