package com.project.viver.repository.user;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.viver.entity.user.Bookmark;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, String>, BookmarkRepositoryCustom{

//	@Query(value = 
//		    "select "
//		    + "    t.movie_id"                       /*movie_id*/
//		    + "  , t.id "                            /*tmbd_id*/
//		    + "  , t.title "                         /*제목*/
//		    + "  , t.original_title"                 /*원제목*/
//		    + "  , t.adult"                          /*성인유무*/
//		    + "  , t.overview"                       /*줄거리*/
//		    + "  , t.popularity"                     /*인기도*/
//		    + "  , t.poster_path"                    /*포스터*/
//		    + "  , t.release_date"                   /*개봉일*/
//		    + "  , t.vote_average "                   /*평점*/
//		    + "from viver.tb_movie t"
//			+ " where t.del_yn = 'N' and"
//			+ " ( t.title LIKE CONCAT('%', :keyword, '%')"
//			+ " OR t.original_title LIKE CONCAT('%', :keyword, '%'))"
//			+ " Order by t.title asc"
//			+ " limit 100", nativeQuery = true)
//	List<Map<String,Object>> getList(@Param("keyword") String keyword);
	
	@Query(value = 
		    "select  "
		    + "	bm.bookmark_id             as bm_id "
		    + "	, bm.user_id               as user_id "
		    + "	, mv.movie_id              as movie_id "
		    + "	, mv.title                 as movie_title "
		    + "	, mv.poster_path           as movie_poster "
		    + "	, tv.tv_id                 as tv_id "
		    + "	, tv.\"name\"              as tv_title "
		    + "	, tv.poster_path           as tv_poster "
		    + "	, ms.mv_id                 as musical_id "
		    + "	, ms.prfnm                 as musical_title "
		    + "	, ms.poster                as musical_poster "
		    + "from viver.tb_bookmark bm  "
		    + "left join viver.tb_movie mv on bm.content_id = mv.movie_id  "
		    + "left join viver.tb_tv tv on bm.content_id = tv.tv_id  "
		    + "left join viver.tb_musical ms on bm.content_id = ms.mv_id  "
		    + "where bm.user_id = '20231005US0000000001' "
		    + "and (mv.del_yn = 'N' or tv.del_yn = 'N' or ms.del_yn = 'N') "
		    + "and (mv.movie_id IS NOT NULL OR tv.tv_id IS NOT NULL OR ms.mv_id IS NOT NULL) "
		    + "and ( LOWER(mv.title) LIKE CONCAT('%', LOWER(:keyword), '%') "
		    + "	  or LOWER(tv.name) LIKE CONCAT('%', LOWER(:keyword), '%') "
		    + "	  or LOWER(ms.prfnm) LIKE CONCAT('%', :keyword, '%') "
		    + "   or LOWER(mv.original_title) LIKE CONCAT('%', LOWER(:keyword), '%') "
		    + "	  or LOWER(tv.original_name) LIKE CONCAT('%', LOWER(:keyword), '%') "
		    + "	  or ms.prfcast LIKE CONCAT('%', :keyword, '%') "
		    + "	  or ms.prfcrew LIKE CONCAT('%', :keyword, '%'))"
			+ " Order by bm.bookmark_id desc"
			+ " limit 6", nativeQuery = true)
	List<Map<String,Object>> getSearchList(@Param("keyword") String keyword);
}
