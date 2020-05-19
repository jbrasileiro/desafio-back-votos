package com.github.jbrasileiro.dainichi.associate;

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

import com.github.jbrasileiro.dainichi.orm.entity.Associate;

class AssociateEndpointTest {

	private static final Integer ONE_INTEGER = 1;

	private static final AssociateTO ANY_ASSOCIATE = AssociateTO.builder().build();

	@Mock
	private AssociateService service;
	private AssociateEndpoint endpoint;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		endpoint = new AssociateEndpoint(service);
	}

	@Test
	void testGet() {
		ResponseEntity<Collection<Associate>> result = endpoint.get();
		Mockito.verify(service, Mockito.times(1)).findAll();
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertTrue(result.getBody().isEmpty());
	}

	@Test
	void testGetInteger() {
		ResponseEntity<Associate> result = endpoint.get(10);
		Mockito.verify(service, Mockito.times(ONE_INTEGER)).findByID(ArgumentMatchers.eq(10));
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNull(result.getBody());
	}

	@Test
	void testSave() {
		ResponseEntity<Associate> result = endpoint.save(ANY_ASSOCIATE);
		Mockito.verify(service, Mockito.times(ONE_INTEGER)).save(ArgumentMatchers.same(ANY_ASSOCIATE));
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNull(result.getBody());
	}

	@Test
	void testUpdate() {
		ResponseEntity<Associate> result = endpoint.update(20, ANY_ASSOCIATE);
		Mockito.verify(service, Mockito.times(ONE_INTEGER)).update(
			ArgumentMatchers.eq(20),
			ArgumentMatchers.same(ANY_ASSOCIATE));
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
