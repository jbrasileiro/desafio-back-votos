package com.github.jbrasileiro.dainichi.associate;

import java.util.Collection;

import com.github.jbrasileiro.dainichi.orm.entity.Associate;

public interface AssociateService {

	Collection<Associate> findAll();

	Associate findByID(
		Integer id);

	Associate save(
		AssociateTO entity);

	Associate update(
		Integer id,
		AssociateTO entity);

	boolean delete(
		Integer id);

	Associate validateAssociateVote(
		Integer idAssociate);
}
