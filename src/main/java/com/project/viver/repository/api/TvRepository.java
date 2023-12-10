package com.project.viver.repository.api;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.viver.entity.tmdb.Drama;


@Repository
public interface TvRepository extends JpaRepository<Drama, String>, TvRepositoryCustom{

	@Query(value =
		    "select "
		    + "    t.tv_id"                       /*drama_id*/
		    + "  , t.id "                            /*id*/
		    + "  , t.name "                          /*제목*/
		    + "  , t.backdrop_path"                  /*배경이미지*/
		    + "  , t.original_language"              /*원어*/
		    + "  , t.original_name"                  /*원제목*/
		    + "  , t.overview"                       /*줄거리*/
		    + "  , t.popularity"                     /*인기도*/
		    + "  , t.poster_path"                    /*포스터*/
		    + "  , t.first_air_date"                 /*?*/
		    + "  , t.vote_average"                   /*평점*/
		    + "from viver.tb_tv t"
			+ " where t.del_yn = 'N' and"
			+ " ( t.name LIKE CONCAT('%', :keyword, '%')"
			+ " OR t.original_name LIKE CONCAT('%', :keyword, '%'))"
			+ " Order by title asc"
			+ " limit 100", nativeQuery = true)
	List<Map<String,Object>> getList(@Param("keyword") String keyword);
	
	@Query(value =
		    "select "
		    + "    t.tv_id               as db_id"                       /*drama_id*/
		    + "  , t.id                  as id"                            /*id*/
		    + "  , t.name                as title"                          /*제목*/
		    + "  , t.poster_path         as poster"                    /*포스터*/
		    + "  , t.vote_average        as vote"                   /*평점*/
		    + " from viver.tb_tv t"
			+ " where t.del_yn = 'N' and"
			+ " ( LOWER(t.name) LIKE CONCAT('%', LOWER(:keyword), '%')"
			+ " OR LOWER(t.original_name) LIKE CONCAT('%', LOWER(:keyword), '%'))"
			+ " Order by t.id desc"
			+ " limit 5", nativeQuery = true)
	List<Map<String,Object>> getSearchList(@Param("keyword") String keyword);
}
