package com.github.jbrasileiro.dainichi.sessionresult.jms.cloudstream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SessionResultOuputSource {

	@Output("sessionResultOutput")
	MessageChannel channel();
}
