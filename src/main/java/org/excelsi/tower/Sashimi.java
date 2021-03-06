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


public class Sashimi extends Comestible implements Combustible {
    public float getSize() {
        return 0.7f;
    }

    public float getWeight() {
        return 0.3f;
    }

    public int getNutrition() {
        return Hunger.RATE;
    }

    public int getFindRate() {
        return 0;
    }

    public float getDecayRate() {
        return 3f;
    }

    public boolean isCombustible() {
        return true;
    }

    public int getCombustionTemperature() {
        return 200;
    }

    public String getCombustionPhrase() {
        return "cooks";
    }

    public void combust(Container c) {
        c.consume(this);
        Steak s = new Steak();
        s.setStatus(getStatus());
        s.setName("broiled fish");
        s.setConsumed(getConsumed());
        for(Fragment f:getFragments()) {
            s.addFragment((Fragment)DefaultNHBot.deepCopy(f));
        }
        c.add(s);
    }
}
