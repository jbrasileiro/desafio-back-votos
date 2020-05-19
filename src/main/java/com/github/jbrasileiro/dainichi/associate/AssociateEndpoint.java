package com.github.jbrasileiro.dainichi.associate;

import java.util.Collection;

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

import com.github.jbrasileiro.dainichi.orm.entity.Associate;

@RestController
@RequestMapping("/associate")
public class AssociateEndpoint {

	private final AssociateService service;

	@Autowired
	public AssociateEndpoint(
		final AssociateService service) {
		super();
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Collection<Associate>> get() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Associate> get(
		@PathVariable final Integer id) {
		return ResponseEntity.ok(service.findByID(id));
	}

	@PostMapping
	public ResponseEntity<Associate> save(
		final @RequestBody AssociateTO request) {
		return ResponseEntity.ok(service.save(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Associate> update(
		@PathVariable final Integer id,
		final @RequestBody AssociateTO request) {
		return ResponseEntity.ok(service.update(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(
		@PathVariable final Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
