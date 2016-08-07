package org.excelsi.sketch;


public class Historian {
    private Context _context;


    public Historian(final Context context) {
        _context = context;
    }

    public void tick() {
        _context.getState().run(_context);
    }
}
