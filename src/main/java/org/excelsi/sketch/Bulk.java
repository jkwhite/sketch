package org.excelsi.sketch;


import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


public class Bulk {
    private final Map<Integer,Level> _levels = new HashMap<>();


    public void addLevel(Level level) {
        _levels.put(level.getOrdinal(), level);
    }

    public Level findLevel(final int ordinal) {
        return _levels.get(ordinal);
    }
}
