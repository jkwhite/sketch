package org.excelsi.sketch;


public class BlockingNarrative implements Narrative {
    private final EventBus _e;


    public BlockingNarrative(final EventBus e) {
        _e = e;
    }

    @Override public void pause() {
    }

    @Override public void message(String m) {
        _e.post(new MessageEvent(this, m));
    }

    @Override public <E> E choose(SelectionMenu<E> m) {
        return _e.await(new SelectEvent<E>(this, m)).getMenu().getChoice().item();
    }
}
