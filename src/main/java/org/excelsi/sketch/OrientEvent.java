package org.excelsi.sketch;


import org.excelsi.matrix.Bot;
import org.excelsi.matrix.Direction;


public class OrientEvent extends BotChangeEvent<Direction> {
    public OrientEvent(Object source, Bot b, Direction from, Direction to) {
        super(source, b, from, to);
    }
}
