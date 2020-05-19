package com.github.jbrasileiro.dainichi.configuration;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import com.github.jbrasileiro.dainichi.sessionresult.jms.cloudstream.SessionResultInputSource;
import com.github.jbrasileiro.dainichi.sessionresult.jms.cloudstream.SessionResultOuputSource;

@EnableBinding({
	SessionResultOuputSource.class,
	SessionResultInputSource.class
})
@Configuration
public class RabbitMQConfiguration {
}
