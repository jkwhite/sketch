package org.excelsi.sketch;


public class BlockingNarrative implements Narrative {
    private final EventBus _e;


    public BlockingNarrative(final EventBus e) {
        _e = e;
    }

    @Override public <E> E choose(SelectionMenu<E> m) {
        return _e.await(new SelectEvent(this, m)).getMenu().getChoice();
    }
}
