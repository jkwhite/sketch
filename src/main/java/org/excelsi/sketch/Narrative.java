package org.excelsi.sketch;


public interface Narrative {
    //void act(Menu<E> m);
    <E> E choose(SelectionMenu<E> m);
}
