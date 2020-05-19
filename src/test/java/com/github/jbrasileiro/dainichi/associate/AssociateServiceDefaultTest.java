package com.github.jbrasileiro.dainichi.associate;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.jbrasileiro.dainichi.commons.ApplicationRuntimeException;
import com.github.jbrasileiro.dainichi.orm.entity.Associate;
import com.github.jbrasileiro.dainichi.orm.repository.AssociateRepository;


class AssociateServiceDefaultTest {

	@Mock
	private CPFValidatorService cpfValidatorService;
	@Mock
	private AssociateRepository repository;
	private AssociateServiceDefault service;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		service = new AssociateServiceDefault(cpfValidatorService, repository);
	}

	@Test
	void save() {
		AssociateTO to = AssociateTO.builder()
				.cpf("00000000000")
				.name("any name 00")
				.build();
		Associate expectedEntity = new Associate();
		expectedEntity.setCpf("00000000000");
		expectedEntity.setName("any name 00");
		Associate result = service.save(to);
		Mockito.verify(repository).save(ArgumentMatchers.eq(expectedEntity));
		Assertions.assertNull(result);
	}

	@Test
	void update() {
		AssociateTO to = AssociateTO.builder()
				.cpf("11111111111")
				.name("any name 01")
				.build();
		Associate expectedEntity = new Associate();
		expectedEntity.setCpf("11111111111");
		expectedEntity.setName("any name 01");
		Associate returnEntity = new Associate();

		Mockito.when(repository.findById(ArgumentMatchers.any(Integer.class)))
			.thenReturn(Optional.of(returnEntity));

		Associate result = service.update(10, to);

		Mockito.verify(repository).findById(ArgumentMatchers.eq(10));
		Mockito.verify(repository).save(ArgumentMatchers.eq(expectedEntity));
		Assertions.assertNull(result);
	}

	@Test
	void validateAssociateVoteValid() {
		Associate returnEntity = new Associate();
		returnEntity.setCpf("999");

		Mockito.when(repository.findById(ArgumentMatchers.any(Integer.class)))
			.thenReturn(Optional.of(returnEntity));
		Mockito.when(cpfValidatorService.validate(ArgumentMatchers.anyString())).thenReturn(true);

		Associate result = service.validateAssociateVote(20);
		Mockito.verify(cpfValidatorService).validate(ArgumentMatchers.eq("999"));
		Assertions.assertSame(returnEntity, result);
	}

	@Test
	void validateAssociateVoteInvalid() {
		Mockito.when(repository.findById(ArgumentMatchers.any(Integer.class)))
			.thenReturn(Optional.of(new Associate()));

		ApplicationRuntimeException exception = Assertions.assertThrows(
			ApplicationRuntimeException.class
			, () -> service.validateAssociateVote(30));
		Assertions.assertEquals("Associate #30 is unable to vote.", exception.getMessage());
	}
}
