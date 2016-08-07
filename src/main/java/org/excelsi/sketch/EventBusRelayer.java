package org.excelsi.sketch;


import org.excelsi.aether.EverythingAdapter;
import org.excelsi.matrix.Bot;
import org.excelsi.matrix.MSpace;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class EventBusRelayer extends EverythingAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(EventBusRelayer.class);


    @Override public void moved(MSpace source, MSpace from, MSpace to, Bot b) {
        post("changes", new MoveEvent(source, b, from, to));
    }

    private static void post(final String topic, final Event e) {
        LOG.debug("posting to {}: {}", topic, e);
        EventBus.instance().post(topic, e);
    }
}
