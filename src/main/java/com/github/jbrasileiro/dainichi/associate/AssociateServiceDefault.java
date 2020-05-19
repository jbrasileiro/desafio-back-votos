package com.github.jbrasileiro.dainichi.associate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jbrasileiro.dainichi.commons.AbstractService;
import com.github.jbrasileiro.dainichi.commons.ApplicationRuntimeException;
import com.github.jbrasileiro.dainichi.orm.entity.Associate;
import com.github.jbrasileiro.dainichi.orm.repository.AssociateRepository;

@Service
public final class AssociateServiceDefault
	extends AbstractService<AssociateRepository, Associate, Integer>
	implements
	AssociateService {

	private final CPFValidatorService cpfValidatorService;

	@Autowired
	public AssociateServiceDefault(
		final CPFValidatorService cpfValidatorService,
		final AssociateRepository repository) {
		super(repository);
		this.cpfValidatorService = cpfValidatorService;
	}

	@Override
	public Associate save(
		final AssociateTO to) {
		String cpf = to.getCpf();
		Associate book = new Associate();
		book.setCpf(cpf);
		book.setName(to.getName());
		return save(book);
	}

	@Override
	public Associate update(
		final Integer id,
		final AssociateTO to) {
		Associate unsavedEntity = findByID(id);
		unsavedEntity.setCpf(to.getCpf());
		unsavedEntity.setName(to.getName());
		return save(unsavedEntity);
	}

	@Override
	public Associate validateAssociateVote(
		final Integer idAssociate) {
		Associate associate = findByID(idAssociate);
		String cpf = associate.getCpf();
		if(cpfValidatorService.validate(cpf)) {
			return associate;
		} else {
			String message = String.format("Associate #30 is unable to vote.", idAssociate);
			throw new ApplicationRuntimeException(message);
		}
	}

}
