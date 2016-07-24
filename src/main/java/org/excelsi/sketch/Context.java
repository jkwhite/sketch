package org.excelsi.sketch;


public final class Context {
    private final Narrative _n;
    private State _state = new NullState();


    public Context(final Narrative n) {
        _n = n;
    }

    public Narrative n() {
        return getN();
    }

    public Narrative getN() {
        return _n;
    }

    public State getState() {
        return _state;
    }

    public void setState(final State state) {
        final State oldValue = _state;
        _state = state;
        EventBus.instance().post(new StateChangeEvent(this, oldValue, _state));
    }

    public Context state(final State state) {
        setState(state);
        return this;
    }
}
