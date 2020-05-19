package com.github.jbrasileiro.dainichi.associate;

import java.util.Collections;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Service
public final class CPFValidatorServiceDefault
	implements
	CPFValidatorService {

	private final String url;
	private final Boolean validateCPF;
	private final RestTemplate restTemplate;

	@Autowired
	public CPFValidatorServiceDefault(
		@Value("${external.users.cpf.url}") final String url,
		@Value("${custom.validate.cpf}") final Boolean validateCPF,
		final RestTemplate restTemplate) {
		super();
		this.url = url;
		this.validateCPF = validateCPF;
		this.restTemplate = restTemplate;
	}

	@Override
	public boolean validate(
		final String cpf) {
		if(BooleanUtils.isNotTrue(validateCPF)) {
			return true;
		}
		UsersCpfResponse response = requestUserStatus(cpf);
		return "ABLE_TO_VOTE".equalsIgnoreCase(response.getStatus());
	}

	private UsersCpfResponse requestUserStatus(
		final String cpf) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		String resource = UriComponentsBuilder.fromHttpUrl(url).fragment(cpf).toUriString();
		try {
			ResponseEntity<UsersCpfResponse> response = restTemplate.exchange(resource, HttpMethod.GET, entity, UsersCpfResponse.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			return handleException(e);
		}
	}

	private UsersCpfResponse handleException(
		final HttpClientErrorException exception) {
		if(HttpStatus.NOT_FOUND.value() == exception.getRawStatusCode()) { //wtf 404
			return UsersCpfResponse.builder().build();
		} else {
			throw exception;
		}
	}

}

@AllArgsConstructor
@Builder
@Data
class UsersCpfResponse {

	private String status;
}
