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


import org.excelsi.matrix.*;
import org.excelsi.aether.*;


public class Pits extends FeatureMixin {
    private static int _initChance = 50;
    private static int _subChance = 25;


    public static void setInitialChance(int chance) {
        _initChance = chance;
    }

    public static void setSubsequentChance(int chance) {
        _subChance = chance;
    }

    public void mix(Object o) {
        while(Rand.d100(_initChance)) {
            super.mix(o);
        }
    }

    protected void addFeature(NHSpace space) {
        replace(space);
    }

    private void replace(NHSpace s) {
        s.replace(new Pit());
        for(MSpace m:s.cardinal()) {
            if(m!=null) {
                NHSpace sp = (NHSpace) m;
                if(sp.getClass()==Floor.class &&
                    sp.getParasites().isEmpty() &&
                    Rand.d100(_subChance)) {
                    replace(sp);
                }
            }
        }
    }
}
