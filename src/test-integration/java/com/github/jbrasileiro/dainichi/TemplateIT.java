package com.github.jbrasileiro.dainichi;

import org.junit.Rule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.junit.BrokerRunning;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Template for integration test class (Component level/Black-box)
 */
@ActiveProfiles("IT")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
	classes = {
		DainichiVotingApiApplication.class
		, RabbitMQConfigurationIT.class
	},
	webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(
	classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TemplateIT {

	@Rule
    public static BrokerRunning brokerRunning = BrokerRunning.isRunning();

    @Autowired
    RabbitListenerTestHarness harness;
}
