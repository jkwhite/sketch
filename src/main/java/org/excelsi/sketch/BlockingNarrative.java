package org.excelsi.sketch;


public class BlockingNarrative implements Narrative {
    private final EventBus _e;


    public BlockingNarrative(final EventBus e) {
        _e = e;
    }

    @Override public void pause() {
        EventBus.instance().await("keys", new PauseEvent(this));
    }

    @Override public void title(String title) {
        _e.post("keys", new TitleEvent(this, title));
    }

    @Override public void message(String m) {
        _e.post("keys", new MessageEvent(this, MessageEvent.Type.ephemeral, m));
    }

    @Override public void poster(String m) {
        _e.post("keys", new MessageEvent(this, MessageEvent.Type.permanent, m));
    }

    @Override public <E> E choose(SelectionMenu<E> m) {
        return _e.await("keys", new SelectEvent<E>(this, m)).getMenu().getChoice().item();
    }
}
