package com.project.viver.repository.api;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.viver.entity.kopis.Musical;


@Repository
public interface KopisRepository extends JpaRepository<Musical, String>{

	@Query(value = "SELECT * from tb_musical t"
			+ " where t.del_yn = 'N' and"
			+ " ( t.prfnm LIKE CONCAT('%', :keyword, '%')"
			+ " OR t.prfcast LIKE CONCAT('%', :keyword, '%')"
			+ " OR t.prfcrew LIKE CONCAT('%', :keyword, '%'))", nativeQuery = true)
	List<Map<String,Object>> getList(@Param("keyword") String keyword);
}
