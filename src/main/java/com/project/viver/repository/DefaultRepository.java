package com.project.viver.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Table;

public class DefaultRepository {
	
	@PersistenceContext
	protected EntityManager em;
	
	protected <T> Page<T> searchPage(Class<T> resultClass, List<String> where, Map<String, Object> params, List<String> orders, Pageable pageable) {
	   Table table = (Table)AnnotationUtils.findAnnotation(resultClass, Table.class);
	   String tableName = null;
	   if (table != null) {
	      tableName = table.name();
	   }
	   StringBuilder selectSql = new StringBuilder();
	   selectSql.append(" SELECT t.* FROM  " + tableName + " t ");
	   selectSql.append(" WHERE  1=1 \n");
	   if (!CollectionUtils.isEmpty(where)) {
	      selectSql.append(" AND " + StringUtils.join(where, " AND "));
	   }

	   StringBuilder countSql = new StringBuilder();
	   countSql.append(" SELECT COUNT(*) FROM  " + tableName + " t ");
	   countSql.append(" WHERE  1=1 \n");
	   if (!CollectionUtils.isEmpty(where)) {
	      countSql.append(" AND " + StringUtils.join(where, " AND "));
	   }
	   if (!CollectionUtils.isEmpty(orders)) {
	      selectSql.append(" ORDER BY " + StringUtils.join(orders, " , "));
	   }
	   Query selectQuery = this.em.createNativeQuery(selectSql.toString(), resultClass);
	   this.addParameters(selectQuery, params);
	   selectQuery.setFirstResult((int)pageable.getOffset());
	   selectQuery.setMaxResults(pageable.getPageSize());

	   Query countQuery = this.em.createNativeQuery(countSql.toString());
	   this.addParameters(countQuery, params);

	   return new PageImpl(selectQuery.getResultList(), pageable, ((Number)countQuery.getSingleResult()).longValue());
	}

	protected void addParameters(Query query, Map<String, Object> params) {
	   Iterator var3 = params.entrySet().iterator();      
	   while(var3.hasNext()) {         
		  Entry<String, Object> e = (Entry)var3.next();
	      query.setParameter(e.getKey(), e.getValue());
	   }
	}
}
