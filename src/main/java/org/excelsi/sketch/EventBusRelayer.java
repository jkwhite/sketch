package org.excelsi.sketch;


import org.excelsi.aether.EverythingAdapter;
import org.excelsi.matrix.Bot;
import org.excelsi.matrix.Direction;
import org.excelsi.matrix.MSpace;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import static org.excelsi.sketch.Events.TOPIC_CHANGES;


public class EventBusRelayer extends EverythingAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(EventBusRelayer.class);


    @Override public void moved(MSpace source, MSpace from, MSpace to, Bot b) {
        post(TOPIC_CHANGES, new MoveEvent(source, b, from, to));
    }

    @Override public void faced(Bot b, Direction old, Direction d) {
        post(TOPIC_CHANGES, new OrientEvent(b.getEnvironment().getSpace(), b, old, d));
    }

    private static void post(final String topic, final Event e) {
        LOG.debug("posting to {}: {}", topic, e);
        EventBus.instance().post(topic, e);
    }
}
