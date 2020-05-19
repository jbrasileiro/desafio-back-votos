package com.github.jbrasileiro.dainichi.sessionresult;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.jbrasileiro.dainichi.commons.to.VotingResultTO;
import com.github.jbrasileiro.dainichi.session.SessionResultService;


class SessionResultEndpointTest {

	@Mock
	private SessionResultService service;
	private SessionResultEndpoint endpoint;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		endpoint = new SessionResultEndpoint(service);
	}

	@Test
	void testResult() {
		VotingResultTO result = endpoint.result(10);
		Mockito.verify(service, Mockito.times(1)).findResultVotingBySession(ArgumentMatchers.eq(10));
		Assertions.assertNull(result);
	}
}
