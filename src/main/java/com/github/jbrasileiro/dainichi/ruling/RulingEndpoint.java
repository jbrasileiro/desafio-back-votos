package com.github.jbrasileiro.dainichi.ruling;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbrasileiro.dainichi.orm.entity.Ruling;

@RestController
@RequestMapping("/ruling")
public class RulingEndpoint {

	private final RulingService service;

	@Autowired
	public RulingEndpoint(
		final RulingService service) {
		super();
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Collection<Ruling>> get() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ruling> get(
		@PathVariable final Integer id) {
		return ResponseEntity.ok(service.findByID(id));
	}

	@PostMapping
	public ResponseEntity<Ruling> save(
		final @RequestBody @Valid Ruling request) {
		return ResponseEntity.ok(service.save(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Ruling> update(
		@PathVariable final Integer id,
		final @RequestBody @Valid Ruling request) {
		return ResponseEntity.ok(service.update(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(
		@PathVariable final Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
