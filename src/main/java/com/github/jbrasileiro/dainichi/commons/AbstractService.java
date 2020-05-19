package com.github.jbrasileiro.dainichi.commons;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * Default implementation for services with DB operation.
 *
 * @param <E>
 * @param <ID>
 */
public abstract class AbstractService<R extends JpaRepository<E, ID>, E, ID> {

	private final R repository;

	public AbstractService(
		final R repository) {
		this.repository = repository;
	}

	public final R getRepository() {
		return repository;
	}

	public Collection<E> findAll() {
		return repository.findAll();
	}

	public E findByID(
		final ID id) {
		return repository
				.findById(id)
				.orElseThrow(() -> new ApplicationRuntimeException(String.format("no entity with id {%s}", id)));
	}

	protected E save(
		final E entity) {
		return repository.save(entity);
	}

	public boolean delete(
		final ID id) {
		repository.deleteById(id);
		return true;
	}
}
