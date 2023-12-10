package com.project.viver.repository.api;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.viver.entity.kopis.Musical;


@Repository
public interface MusicalRepository extends JpaRepository<Musical, String>{

	@Query(value = 
			  "SELECT "
			+ "    t.mv_id"                         /*db id*/
			+ "  , t.mt20id"                        /*공연 id*/
			+ "  , t.prfnm"                         /*공연명*/
			+ "  , t.genrenm"                       /*장르명*/
			+ "  , t.poster"                        /*포스터url*/
			+ "  , t.prfpdfrom"                     /*공연시작일*/
			+ "  , t.prfpdto"                       /*공연종료일*/
			+ "  , t.prfruntime"                    /*공연런타임*/
			+ "  , t.prfcast"                       /*출연진*/
			+ "  , t.dtguidance"                    /*공연시간*/
			+ "  , t.sty"                           /*줄거리*/
			+ "  , t.fcitynm"                       /*공연시설명*/
			+ "  , t.pcseguidance"                  /*티켓 가격*/
			+ "  , t.prfstate "                     /*공연 상태*/
			+ " from viver.tb_musical t"
			+ " where t.del_yn = 'N' and"
			+ " ( t.prfnm LIKE CONCAT('%', :keyword, '%')"
			+ " OR t.prfcast LIKE CONCAT('%', :keyword, '%')"
			+ " OR t.prfcrew LIKE CONCAT('%', :keyword, '%'))"
			+ " Order by prfnm asc"
			+ " limit 100", nativeQuery = true)
	List<Map<String,Object>> getList(@Param("keyword") String keyword);
	
	@Query(value = 
			  "SELECT "
			+ "    t.mv_id                as db_id"                         /*db id*/
			+ "  , t.mt20id               as id"                        /*공연 id*/
			+ "  , t.prfnm                as title"                         /*공연명*/
			+ "  , t.poster               as poster"                        /*포스터url*/
			+ " from viver.tb_musical t"
			+ " where t.del_yn = 'N' and"
			+ " ( LOWER(t.prfnm) LIKE CONCAT('%', :keyword, '%')"
			+ " OR t.prfcast LIKE CONCAT('%', :keyword, '%')"
			+ " OR t.prfcrew LIKE CONCAT('%', :keyword, '%'))"
			+ " Order by t.mt20id desc"
			+ " limit 6", nativeQuery = true)
	List<Map<String,Object>> getSearchList(@Param("keyword") String keyword);
	
}
