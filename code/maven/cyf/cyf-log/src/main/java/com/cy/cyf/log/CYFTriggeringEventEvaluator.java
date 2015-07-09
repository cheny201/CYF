package com.cy.cyf.log;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;

public class CYFTriggeringEventEvaluator implements TriggeringEventEvaluator{
	@Override
	public boolean isTriggeringEvent(LoggingEvent arg0) {
		return arg0.getLevel().isGreaterOrEqual(Level.DEBUG);
	}
}