package com.github.jbrasileiro.dainichi.session;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.jbrasileiro.dainichi.orm.entity.Session;


class SessionEndpointTest {

	private static final SessionOpenRequest ANY_REQUEST = SessionOpenRequest.builder().build();

	@Mock
	private SessionService service;
	private SessionEndpoint endpoint;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		endpoint = new SessionEndpoint(service);

	}

	@Test
	void testSearchAll() {
		Collection<Session> result = endpoint.searchAll();
		Mockito.verify(service, Mockito.times(1)).findAll();
		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	void testOpenSession() {
		Session result = endpoint.openSession(ANY_REQUEST);
		Mockito.verify(service, Mockito.times(1)).openSession(ArgumentMatchers.same(ANY_REQUEST));
		Assertions.assertNull(result);
	}
}
