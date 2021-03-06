package org.excelsi.sketch;


public interface Narrative {
    void pause();
    void title(String m);
    void message(String m);
    boolean confirm(String m);
    void poster(String m);
    //void act(Menu<E> m);
    <E> E choose(SelectionMenu<E> m);
}
