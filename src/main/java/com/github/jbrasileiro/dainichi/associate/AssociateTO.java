package com.github.jbrasileiro.dainichi.associate;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public final class AssociateTO {

	@NotEmpty
	private String name;
	@NotEmpty
	private String cpf;
}
