package com.project.viver.repository.common;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonRepository {

	@Query(  value = 
			   " SELECT TO_CHAR(SYSDATE, 'yyyyMMdd') || :word || LPAD(SEQ_ID.NEXTVAL,11,'0') "
			 + " FROM DUAL"
		   , nativeQuery = true)
	public String getId(String word);
}
