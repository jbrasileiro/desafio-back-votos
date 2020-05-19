package com.github.jbrasileiro.dainichi.ruling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jbrasileiro.dainichi.commons.AbstractService;
import com.github.jbrasileiro.dainichi.orm.entity.Ruling;
import com.github.jbrasileiro.dainichi.orm.repository.RulingRepository;


@Service
public class RulingServiceDefault
	extends
	AbstractService<RulingRepository, Ruling, Integer>
	implements
	RulingService {

	@Autowired
	public RulingServiceDefault(
		final RulingRepository repository) {
		super(repository);
	}

	@Override
	public Ruling save(
		final Ruling entity) {
		return super.save(entity);
	}

	@Override
	public Ruling update(
		final Integer id,
		final Ruling entity) {
		Ruling unsavedEntity = findByID(id);
		unsavedEntity.setName(entity.getName());
		return save(unsavedEntity);
	}
}
