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


import org.excelsi.aether.*;
import org.excelsi.matrix.*;


public class FollowSenseDaemon extends FollowDaemon {
    public FollowSenseDaemon() {
    }

    public boolean accept(MSpace s) {
        NHSpace m = (NHSpace) s;
        if(m.numItems()>0) {
            for(Item i:m.getItem()) {
                if(i.getStatus()==Status.cursed) {
                    return false;
                }
            }
            for(Parasite p:m.getParasites()) {
                if(p instanceof Trap) {
                    return false;
                }
            }
        }
        return true;
    }
}
