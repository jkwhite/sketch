/*
    Tower
    Copyright (C) 2007, John K White, All Rights Reserved
*/
/*
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
*/
package org.excelsi.tower;


import org.excelsi.matrix.MSpace;
import org.excelsi.matrix.Direction;
import org.excelsi.aether.*;
import java.util.List;


public abstract class PlantSeed extends Seed {
    private List<Fragment> _fragments;


    public PlantSeed(List<Fragment> fragments) {
        _fragments = fragments;
    }

    protected final Parasite next() {
        N.narrative().print(getSpace(), "The seed sprouts.");
        Plant p = toPlant();
        if(_fragments!=null) {
            for(Fragment i:_fragments) {
                p.addFragment((Fragment)DefaultNHBot.deepCopy(i));
            }
        }
        return p;
    }

    abstract protected Plant toPlant();
}
