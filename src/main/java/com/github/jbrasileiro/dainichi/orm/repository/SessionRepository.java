package com.github.jbrasileiro.dainichi.orm.repository;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.jbrasileiro.dainichi.orm.entity.Session;

@Repository
public interface SessionRepository
	extends
	JpaRepository<Session, Integer> {

	Collection<RulingRepository> findByRuling(
		Integer idRulling);


	@Query("SELECT session "
		+ " FROM Session session"
		+ " INNER JOIN session.ruling ruling"
		+ " WHERE ruling.id = :idRulling")
	Session findByIdRulling(
		Integer idRulling);

	Collection<Session> findByTimeEndBeforeAndSendFalseOrSendIsNull(
		LocalDateTime now);

}
