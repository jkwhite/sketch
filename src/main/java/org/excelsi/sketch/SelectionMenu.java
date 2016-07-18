package org.excelsi.sketch;


public class SelectionMenu<E> extends Menu<E> {
    public SelectionMenu(MenuItem<E>... items) {
        super((sel)->{ setChoice(sel); return sel; }, items);
    }
}
