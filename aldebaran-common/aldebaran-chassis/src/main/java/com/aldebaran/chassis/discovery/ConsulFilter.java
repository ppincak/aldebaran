package com.aldebaran.chassis.discovery;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;


public class ConsulFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if(event.getThrowableProxy().getClassName().equals("java.net.SocketTimeoutException")) {
            return FilterReply.DENY;
        }
        return FilterReply.ACCEPT;
    }
}
