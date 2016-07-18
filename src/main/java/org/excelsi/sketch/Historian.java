package org.excelsi.sketch;


public class Historian implements Temporal {
    private Context _context;


    public Historian(final Context context) {
        _context = context;
    }

    @Override public void tick() {
        _context.getState().run(_context);
    }
}
