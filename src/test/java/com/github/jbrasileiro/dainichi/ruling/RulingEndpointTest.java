package com.github.jbrasileiro.dainichi.ruling;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.jbrasileiro.dainichi.orm.entity.Ruling;


class RulingEndpointTest {

	private static final Integer ONE_INTEGER = 1;
	private static final Ruling ANY_RULLING = new Ruling();

	@Mock
	private RulingService service;
	private RulingEndpoint endpoint;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		endpoint = new RulingEndpoint(service);
	}

	@Test
	void testGet() {
		ResponseEntity<Collection<Ruling>> result = endpoint.get();
		Mockito.verify(service, Mockito.times(1)).findAll();
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertTrue(result.getBody().isEmpty());
	}

	@Test
	void testGetInteger() {
		ResponseEntity<Ruling> result = endpoint.get(10);
		Mockito.verify(service, Mockito.times(ONE_INTEGER)).findByID(ArgumentMatchers.eq(10));
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNull(result.getBody());
	}

	@Test
	void testSave() {
		ResponseEntity<Ruling> result = endpoint.save(ANY_RULLING);
		Mockito.verify(service, Mockito.times(ONE_INTEGER)).save(ArgumentMatchers.same(ANY_RULLING));
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNull(result.getBody());
	}

	@Test
	void testUpdate() {
		ResponseEntity<Ruling> result = endpoint.update(20, ANY_RULLING);
		Mockito.verify(service, Mockito.times(ONE_INTEGER)).update(
			ArgumentMatchers.eq(20),
			ArgumentMatchers.same(ANY_RULLING));
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNull(result.getBody());
	}

	@Test
	void testDelete() {
		ResponseEntity<Object> result = endpoint.delete(30);
		Mockito.verify(service, Mockito.times(ONE_INTEGER)).delete(ArgumentMatchers.eq(30));
		Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
		Assertions.assertNull(result.getBody());
	}
}
