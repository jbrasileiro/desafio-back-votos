package com.github.jbrasileiro.dainichi.associate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

class CPFValidatorServiceDefaultTest {

	private static final String URL = "http://localhost:9999";
	@Mock
	private RestTemplate restTemplate;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	private CPFValidatorServiceDefault service(
		final Boolean validateCPF) {
		return new CPFValidatorServiceDefault(URL, validateCPF, restTemplate);
	}

	@SuppressWarnings("unchecked")
	@Test
	void testValidateCPFWithVoteEnable() {
		UsersCpfResponse expected = new UsersCpfResponse("ABLE_TO_VOTE");
		Mockito.when(restTemplate.exchange(
			ArgumentMatchers.anyString()
			, ArgumentMatchers.any(HttpMethod.class)
			, ArgumentMatchers.any(HttpEntity.class)
			, (Class<Object>) ArgumentMatchers.any()))
			.thenReturn(ResponseEntity.ok(expected));
		boolean result = service(true).validate("00");
		Assertions.assertTrue(result);
	}

	@SuppressWarnings("unchecked")
	@Test
	void testValidateCPFWithNoVoteEnable() {
		UsersCpfResponse expected = new UsersCpfResponse("-");
		Mockito.when(restTemplate.exchange(
			ArgumentMatchers.anyString()
			, ArgumentMatchers.any(HttpMethod.class)
			, ArgumentMatchers.any(HttpEntity.class)
			, (Class<Object>) ArgumentMatchers.any()))
			.thenReturn(ResponseEntity.ok(expected));
		boolean result = service(true).validate("01");
		Mockito.verify(restTemplate).exchange(
			ArgumentMatchers.eq("http://localhost:9999#01")
			, ArgumentMatchers.any(HttpMethod.class)
			, ArgumentMatchers.any(HttpEntity.class)
			, (Class<Object>) ArgumentMatchers.any());
		Assertions.assertFalse(result);
	}

	@Test
	void testValidateWithNoValidation() {
		boolean result = service(false).validate("02");
		Assertions.assertTrue(result);
	}

	@SuppressWarnings("unchecked")
	@Test
	void testValidateHandling404() {
		Mockito.when(restTemplate.exchange(
			ArgumentMatchers.anyString()
			, ArgumentMatchers.any(HttpMethod.class)
			, ArgumentMatchers.any(HttpEntity.class)
			, (Class<Object>) ArgumentMatchers.any()))
		.thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

		boolean result = service(true).validate("03");

		Mockito.verify(restTemplate).exchange(
			ArgumentMatchers.eq("http://localhost:9999#03")
			, ArgumentMatchers.any(HttpMethod.class)
			, ArgumentMatchers.any(HttpEntity.class)
			, (Class<Object>) ArgumentMatchers.any());
		Assertions.assertFalse(result);
	}

	@SuppressWarnings("unchecked")
	@Test
	void testValidateHandlingAnyError() {
		Mockito.when(restTemplate.exchange(
			ArgumentMatchers.anyString()
			, ArgumentMatchers.any(HttpMethod.class)
			, ArgumentMatchers.any(HttpEntity.class)
			, (Class<Object>) ArgumentMatchers.any()))
			.thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));


		Assertions.assertThrows(HttpClientErrorException.class, () -> service(true).validate("04"));

		Mockito.verify(restTemplate).exchange(
			ArgumentMatchers.eq("http://localhost:9999#04")
			, ArgumentMatchers.any(HttpMethod.class)
			, ArgumentMatchers.any(HttpEntity.class)
			, (Class<Object>) ArgumentMatchers.any());
	}
}
