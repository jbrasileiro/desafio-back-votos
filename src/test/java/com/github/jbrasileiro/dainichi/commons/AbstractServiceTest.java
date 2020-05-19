package com.github.jbrasileiro.dainichi.commons;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.jbrasileiro.testing.UTEntity;
import com.github.jbrasileiro.testing.UTRepository;


class AbstractServiceTest {

	private static final UTEntity ANY_ENTITY = new UTEntity();
	private static final int ONE_INTEGER = 1;

	private class UTService
		extends
		AbstractService<UTRepository, UTEntity, Integer> {

		public UTService(
			final UTRepository repository) {
			super(repository);
		}
	}

	@Mock
	private UTRepository repository;
	private UTService service;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		service = new UTService(repository);
	}

	@Test
	void testFindAll() {
		Collection<UTEntity> result = service.findAll();
		Mockito.verify(repository, Mockito.times(ONE_INTEGER)).findAll();
		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	void testFindByIDWhenEntityFound() {
		UTEntity expected = new UTEntity();
		Optional<UTEntity> returnEntity = Optional.of(expected);
		Mockito.when(repository.findById(ArgumentMatchers.any(Integer.class))).thenReturn(returnEntity);
		UTEntity result = service.findByID(10);
		Mockito.verify(repository, Mockito.times(ONE_INTEGER)).findById(ArgumentMatchers.eq(10));
		Assertions.assertSame(expected, result);
	}

	@Test
	void testFindByIDWhenNoEntityFound() {
		ApplicationRuntimeException exception = Assertions.assertThrows(ApplicationRuntimeException.class, () -> service.findByID(11));
		Mockito.verify(repository, Mockito.times(ONE_INTEGER)).findById(ArgumentMatchers.eq(11));
		Assertions.assertEquals("no entity with id {11}", exception.getMessage());
	}

	@Test
	void testSave() {
		UTEntity result = service.save(ANY_ENTITY);
		Mockito.verify(repository, Mockito.times(ONE_INTEGER)).save(ArgumentMatchers.same(ANY_ENTITY));
		Assertions.assertNull(result);
	}

	@Test
	void testDelete() {
		boolean result = service.delete(30);
		Mockito.verify(repository, Mockito.times(ONE_INTEGER)).deleteById(ArgumentMatchers.eq(30));
		Assertions.assertTrue(result);
	}
}
