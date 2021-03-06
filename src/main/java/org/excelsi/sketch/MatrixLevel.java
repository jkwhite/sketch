package org.excelsi.sketch;


import org.excelsi.matrix.Matrix;
import org.excelsi.matrix.Id;
import org.excelsi.matrix.MSpaceListener;
import org.excelsi.matrix.MatrixListener;
import org.excelsi.matrix.MSpace;
import org.excelsi.matrix.Bot;
import org.excelsi.matrix.Actor;
import org.excelsi.aether.NHBot;


public class MatrixLevel extends Id implements Level, MatrixListener, MSpaceListener {
    private final String _name;
    private final int _ordinal;
    private final Matrix _m;
    private final ActQueue _queue = new ActQueue();


    public MatrixLevel(String name, int ordinal, Matrix m) {
        _name = name;
        _ordinal = ordinal;
        _m = m;
        _m.addMatrixListener(this);
        for(MSpace ms:_m.spaces()) {
            if(ms!=null) {
                ms.addMSpaceListener(this);
                if(ms.getOccupant()!=null) {
                    _queue.add((NHBot)ms.getOccupant());
                }
            }
        }
    }

    @Override public void tick(final Context c) {
        NHBot first = null;
        while(true) {
            NHBot acting = _queue.next();
            if(first!=null && first==acting) {
                break;
            }
            if(acting.isPlayer()) {
                acting.getEnvironment().unhide();
                //break;
            }
            Actor.setCurrent(acting);
            c.setActor(acting);
            acting.act(c);
            c.setActor(null);
            Actor.setCurrent(null);
            if(first==null) {
                first = acting;
            }
        }
        for(NHBot b:_queue.getBots()) {
            Actor.setCurrent(b);
            b.tick();
            Actor.setCurrent(null);
        }
        for(MSpace m:_m.spaces()) {
            if(m!=null) {
                m.update();
            }
        }
    }

    @Override public String getObjectType() {
        return "level";
    }

    @Override public String getName() {
        return _name;
    }

    @Override public int getOrdinal() {
        return _ordinal;
    }

    @Override public Matrix getMatrix() {
        return _m;
    }

    @Override public void spacesRemoved(Matrix m, MSpace[] spaces, Bot remover) {
        for(final MSpace ms:spaces) {
            ms.removeMSpaceListener(this);
        }
    }

    @Override public void spacesAdded(Matrix m, MSpace[] spaces, Bot adder) {
        for(final MSpace ms:spaces) {
            ms.addMSpaceListener(this);
        }
    }

    @Override public void attributeChanged(Matrix m, String attr, Object oldValue, Object newValue) {
    }

    @Override public void occupied(MSpace s, Bot b) {
        _queue.add((NHBot)b);
    }

    @Override public void unoccupied(MSpace s, Bot b) {
        _queue.remove((NHBot)b);
    }

    @Override public void moved(MSpace source, MSpace from, MSpace to, Bot b) {
    }

    @Override public String toString() {
        return "level::{name:\""+_name+"\", ordinal:"+_ordinal+"}";
    }
}
