package com.project.viver.repository.api;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.viver.entity.tmdb.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, String>, MovieRepositoryCustom{

	@Query(value = 
		    "select "
		    + "    t.movie_id"                       /*movie_id*/
		    + "  , t.id "                            /*tmbd_id*/
		    + "  , t.title "                         /*제목*/
		    + "  , t.original_title"                 /*원제목*/
		    + "  , t.adult"                          /*성인유무*/
		    + "  , t.overview"                       /*줄거리*/
		    + "  , t.popularity"                     /*인기도*/
		    + "  , t.poster_path"                    /*포스터*/
		    + "  , t.release_date"                   /*개봉일*/
		    + "  , t.vote_average "                   /*평점*/
		    + "from viver.tb_movie t"
			+ " where t.del_yn = 'N' and"
			+ " ( t.title LIKE CONCAT('%', :keyword, '%')"
			+ " OR t.original_title LIKE CONCAT('%', :keyword, '%'))"
			+ " Order by t.title asc"
			+ " limit 100", nativeQuery = true)
	List<Map<String,Object>> getList(@Param("keyword") String keyword);
	
	@Query(value = 
		    "select "
		    + "    t.movie_id"                       /*movie_id*/
		    + "  , t.id "                            /*tmbd_id*/
		    + "  , t.title "                         /*제목*/
		    + "  , t.original_title"                 /*원제목*/
		    + "  , t.adult"                          /*성인유무*/
		    + "  , t.poster_path"                    /*포스터*/
		    + " from viver.tb_movie t"
			+ " where t.del_yn = 'N' and"
			+ " ( t.title LIKE CONCAT('%', :keyword, '%')"
			+ " OR t.original_title LIKE CONCAT('%', :keyword, '%'))"
			+ " Order by t.title asc"
			+ " limit 10", nativeQuery = true)
	List<Map<String,Object>> getSearchList(@Param("keyword") String keyword);
}
