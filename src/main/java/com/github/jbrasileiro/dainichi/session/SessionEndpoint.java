package com.github.jbrasileiro.dainichi.session;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbrasileiro.dainichi.orm.entity.Session;

@RestController
@RequestMapping("/session")
public class SessionEndpoint {

	private final SessionService service;

	@Autowired
	public SessionEndpoint(
		final SessionService service) {
		super();
		this.service = service;
	}

	@GetMapping
	public Collection<Session> searchAll() {
		return service.findAll();
	}

	@PostMapping("open")
	public Session openSession(
		@RequestBody @Valid final SessionOpenRequest request) {
		return service.openSession(request);
	}
}
