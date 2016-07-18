package org.excelsi.sketch;


public interface Narrative {
    void message(String m);
    //void act(Menu<E> m);
    <E> E choose(SelectionMenu<E> m);
}
