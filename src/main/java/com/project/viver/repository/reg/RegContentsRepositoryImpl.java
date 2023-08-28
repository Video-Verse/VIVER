package com.project.viver.repository.reg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import com.project.viver.entity.reg.RegContents;
import com.project.viver.repository.DefaultRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


public class RegContentsRepositoryImpl extends DefaultRepository implements RegContentsRepositoryCustom {
	
	@PersistenceContext
	protected EntityManager em;

	public Page<RegContents> search(Map<String, String> params, PageRequest pageable){
		
		Map<String, Object> queryParams = new HashMap<>();
		List<String> where = new ArrayList<>();
		List<String> orders = new ArrayList<>();
		
		where.add("( t.del_yn = :delYn )");
		queryParams.put("delYn", "N");
		
		String prop = params.get("prop");
		String order = params.get("order");
		
		if(StringUtils.isNotBlank(order) && StringUtils.isNotBlank(prop)) {
			Direction direction = Direction.DESC;
			if(StringUtils.equals("ascending", order)) {
				direction = Direction.ASC;
			}
			orders.add(prop + " " + direction.name());
		} else {
			orders.add(" t.crt_dt DESC ");
		}
		
		return searchPage(RegContents.class, where, queryParams, orders, pageable);
	}
}
