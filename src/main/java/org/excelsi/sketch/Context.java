package org.excelsi.sketch;


import org.excelsi.aether.InputSource;
import org.excelsi.aether.NHBot;


public final class Context {
    private final Narrative _n;
    private State _state = new NullState();
    private InputSource _input;
    private NHBot _actor;


    public Context(final Narrative n, final InputSource input) {
        _n = n;
        _input = input;
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

    public InputSource getInputSource() {
        return _input;
    }

    public void setState(final State state) {
        final State oldValue = _state;
        _state = state;
        EventBus.instance().post("keys", new StateChangeEvent(this, oldValue, _state));
    }

    public Context state(final State state) {
        setState(state);
        return this;
    }

    public Context inputSource(final InputSource input) {
        _input= input;
        return this;
    }

    public void setActor(final NHBot b) {
        _actor = b;
    }

    public NHBot getActor() {
        return _actor;
    }
}
