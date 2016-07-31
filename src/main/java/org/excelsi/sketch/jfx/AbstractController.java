package org.excelsi.sketch.jfx;


public abstract class AbstractController<E> implements Controller<E> {
    private final E _e;


    public AbstractController(final E e) {
        _e = e;
    }

    protected final E e() {
        return _e;
    }
}
