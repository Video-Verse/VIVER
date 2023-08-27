package com.project.viver.repository.common;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class CommonRepository {
	
	@PersistenceContext
	protected EntityManager em;

	public String getId(String word) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT TO_CHAR(current_timestamp, 'yyyyMMdd') || '" + word + "' || lpad(cast(nextval('SEQ_ID') as varchar(10)) , 10, '0')");
		Query selectQuery = em.createNativeQuery(sql.toString());
		return selectQuery.getSingleResult().toString();
		
	};
}
