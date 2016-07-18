package org.excelsi.sketch;


import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Predicate;


public final class EventBus {
    private static final EventBus _b = new EventBus();

    private final Queue<Event> _events = new ArrayBlockingQueue<>(1000);


    public static EventBus instance() {
        return _b;
    }

    public <E extends Event> E await(E e) {
        synchronized(e) {
            _events.add(e);
            try {
                e.wait();
            }
            catch(InterruptedException ex) {
            }
        }
        return e;
    }

    public void post(Event e) {
        _events.add(e);
    }

    public boolean hasEvents() {
        return !_events.isEmpty();
    }

    public void consume(Handler h) {
        while(!_events.isEmpty()) {
            h.handleEvent(_events.remove());
        }
    }

    public interface Handler {
        void handleEvent(Event e);
    }

    private EventBus() {
    }
}
