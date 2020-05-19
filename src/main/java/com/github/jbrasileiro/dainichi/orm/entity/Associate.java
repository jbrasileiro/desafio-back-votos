package com.github.jbrasileiro.dainichi.orm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(
	name = "ASSOCIATE",
	uniqueConstraints = {
		@UniqueConstraint(
			columnNames = {
				"CPF"
			})
	})
public final class Associate
	implements
	Serializable {

	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column(
		name = "CPF",
		unique = true)
	private String cpf;
}
