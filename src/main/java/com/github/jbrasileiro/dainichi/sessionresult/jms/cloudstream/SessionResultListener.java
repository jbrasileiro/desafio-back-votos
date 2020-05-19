package com.github.jbrasileiro.dainichi.sessionresult.jms.cloudstream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class SessionResultListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionResultListener.class);

	@StreamListener("sessionResultInput")
	public void readMessage(
		final String message) {
		LOGGER.info("message received -> {}", message);
	}
}
