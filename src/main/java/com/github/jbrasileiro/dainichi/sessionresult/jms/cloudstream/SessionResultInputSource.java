package com.github.jbrasileiro.dainichi.sessionresult.jms.cloudstream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SessionResultInputSource {

	@Input("sessionResultInput")
	SubscribableChannel input();
}
