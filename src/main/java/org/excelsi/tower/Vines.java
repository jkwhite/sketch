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


public class Vines extends FeatureMixin {
    private static int _initChance = 25;
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

    protected Level.SpaceFilter createFilter() {
        return new Level.SpaceFilter() {
            public boolean accept(MSpace s) {
                if(s==null||(s.getClass()!=Grass.class&&s.getClass()!=Floor.class)) {
                    return false;
                }
                return true;
            }
        };
    }

    protected void addFeature(NHSpace space) {
        entangle(space);
    }

    private void entangle(NHSpace s) {
        s.addParasite(new Vine());
        for(MSpace m:s.cardinal()) {
            if(m!=null) {
                NHSpace sp = (NHSpace) m;
                if((sp.getClass()==Floor.class||sp.getClass()==Grass.class) &&
                    sp.getParasites().isEmpty() &&
                    Rand.d100(_subChance)) {
                    entangle(sp);
                }
            }
        }
    }
}
