package com.github.jbrasileiro.dainichi.orm.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.jbrasileiro.dainichi.commons.enums.VoteEnum;
import com.github.jbrasileiro.dainichi.orm.entity.Voting;

@Repository
public interface VotingRepository
	extends
	JpaRepository<Voting, Integer> {

	@Query("SELECT"
			+ " vote.vote"
			+ " FROM Voting vote"
			+ " WHERE session.id = :id_session"
	)
	Collection<VoteEnum> findResultVotingBySession(
		@Param("id_session") Integer idSession);
}