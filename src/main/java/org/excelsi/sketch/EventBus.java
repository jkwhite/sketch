package org.excelsi.sketch;


import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Predicate;


public final class EventBus {
    private static final EventBus _b = new EventBus();

    private int _nextId;
    private final Map<String,Queue<Event>> _queues = new HashMap<>();
    //private final Queue<Event> _events = new ArrayBlockingQueue<>(1000);


    public static EventBus instance() {
        return _b;
    }

    public String subscribe(final String consumer) {
        final String subscription = consumer+"-"+_nextId++;
        _queues.put(subscription, new ArrayBlockingQueue<>(1000));
        return subscription;
    }

    public <E extends Event> E await(E e) {
        synchronized(e) {
            //_events.add(e);
            post(e);
            try {
                e.wait();
            }
            catch(InterruptedException ex) {
            }
        }
        return e;
    }

    public void post(Event e) {
        //_events.add(e);
        for(final Queue<Event> q:_queues.values()) {
            q.add(e);
        }
    }

    public boolean hasEvents(final String subscription) {
        final Queue<Event> q = _queues.get(subscription);
        if(q==null) {
            throw new IllegalArgumentException("no such subscription '"+subscription+"'");
        }
        return !q.isEmpty();
    }

    public void consume(final String subscription, Handler h) {
        final Queue<Event> q = _queues.get(subscription);
        while(!q.isEmpty()) {
            h.handleEvent(q.remove());
        }
    }

    public interface Handler {
        void handleEvent(Event e);
    }

    private EventBus() {
    }
}
