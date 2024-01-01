package com.project.viver.service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.project.viver.common.constraint.CommonId;
import com.project.viver.entity.kopis.Musical;
import com.project.viver.entity.tmdb.Movie;
import com.project.viver.entity.tmdb.Tv;
import com.project.viver.entity.user.Bookmark;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class BookmarkSpecification {

	 private final EntityManager entityManager;
	    private final CriteriaBuilder criteriaBuilder;

	    public BookmarkSpecification(EntityManager entityManager) {
	        this.entityManager = entityManager;
	        this.criteriaBuilder = entityManager.getCriteriaBuilder();
	    }

	    public List<Map<String, Object>> searchBookmarksByType(Map<String, Object> params) {
	    	
	    	String userId = (String) params.get("userId");
	        String keyword = (String) params.get("keyword");
	        String type = (String) params.get("type");

	        CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();
	        Root<Bookmark> bmRoot = query.from(Bookmark.class);

	        // Define joins based on the 'type' parameter
	        Join<Bookmark, Movie> movieJoin = null;
	        Join<Bookmark, Tv> tvJoin = null;
	        Join<Bookmark, Musical> musicalJoin = null;

	        if (StringUtils.equals(type, CommonId.MOVIE.value())) {
	            movieJoin = bmRoot.join("movie", JoinType.INNER);
	        } else if (StringUtils.equals(type, CommonId.TV.value())) {
	            tvJoin = bmRoot.join("tv", JoinType.INNER);
	        } else if (StringUtils.equals(type, CommonId.MUSICAL.value())) {
	            musicalJoin = bmRoot.join("musical", JoinType.INNER);
	        } else if (StringUtils.equals(type, CommonId.ALL.value())) {
	            movieJoin = bmRoot.join("movie", JoinType.INNER);
	            tvJoin = bmRoot.join("tv", JoinType.INNER);
	            musicalJoin = bmRoot.join("musical", JoinType.INNER);
	        }

	        // Select clause
	        query.multiselect(
	            bmRoot.get("bookmarkId").alias("bm_id"),
	            bmRoot.get("userId").alias("user_id"),
	            movieJoin != null ? movieJoin.get("movie").get("movieId").alias("movie_id") : null,
	            movieJoin != null ? movieJoin.get("movie").get("title").alias("movie_title") : null,
	            movieJoin != null ? movieJoin.get("movie").get("posterPath").alias("movie_poster") : null,
	            tvJoin != null ? tvJoin.get("tvId").alias("tv_id") : null,
	            tvJoin != null ? tvJoin.get("name").alias("tv_title") : null,
	            tvJoin != null ? tvJoin.get("posterPath").alias("tv_poster") : null,
	            musicalJoin != null ? musicalJoin.get("mvId").alias("musical_id") : null,
	            musicalJoin != null ? musicalJoin.get("prfnm").alias("musical_title") : null,
	            musicalJoin != null ? musicalJoin.get("poster").alias("musical_poster") : null
	        );

	        // Where clause
	        Predicate userPredicate = criteriaBuilder.equal(bmRoot.get("userId"), userId);
	        Predicate delPredicate = criteriaBuilder.or(
	            criteriaBuilder.equal(movieJoin != null ? movieJoin.get("delYn") : null, "N"),
	            criteriaBuilder.equal(tvJoin != null ? tvJoin.get("delYn") : null, "N"),
	            criteriaBuilder.equal(musicalJoin != null ? musicalJoin.get("delYn") : null, "N")
	        );
	        Predicate typePredicate = criteriaBuilder.or(
	            criteriaBuilder.isNotNull(movieJoin != null ? movieJoin.get("movieId") : null),
	            criteriaBuilder.isNotNull(tvJoin != null ? tvJoin.get("tvId") : null),
	            criteriaBuilder.isNotNull(musicalJoin != null ? musicalJoin.get("mvId") : null)
	        );
	        Predicate keywordPredicate = criteriaBuilder.or(
	            criteriaBuilder.like(criteriaBuilder.lower(movieJoin != null ? movieJoin.get("movie").get("title") : null), "%" + keyword.toLowerCase() + "%"),
	            criteriaBuilder.like(criteriaBuilder.lower(tvJoin != null ? tvJoin.get("name") : null), "%" + keyword.toLowerCase() + "%"),
	            criteriaBuilder.like(criteriaBuilder.lower(musicalJoin != null ? musicalJoin.get("prfnm") : null), "%" + keyword + "%"),
	            criteriaBuilder.like(criteriaBuilder.lower(movieJoin != null ? movieJoin.get("originalTitle") : null), "%" + keyword.toLowerCase() + "%"),
	            criteriaBuilder.like(criteriaBuilder.lower(tvJoin != null ? tvJoin.get("originalName") : null), "%" + keyword.toLowerCase() + "%"),
	            criteriaBuilder.like(criteriaBuilder.lower(musicalJoin != null ? musicalJoin.get("prfcast") : null), "%" + keyword + "%"),
	            criteriaBuilder.like(criteriaBuilder.lower(musicalJoin != null ? musicalJoin.get("prfcrew") : null), "%" + keyword + "%")
	        );

	        query.where(criteriaBuilder.and(userPredicate, delPredicate, typePredicate, keywordPredicate));

	        // Order by
	        query.orderBy(criteriaBuilder.desc(bmRoot.get("bookmarkId")));

	        TypedQuery<Tuple> typedQuery = entityManager.createQuery(query);
	        List<Tuple> resultList = typedQuery.getResultList();

	        // Convert Tuple results to List<Map<String, Object>>
	        List<Map<String, Object>> resultMapList = new ArrayList<>();
	        for (Tuple tuple : resultList) {
	            Map<String, Object> resultMap = new HashMap<>();
	            for (TupleElement<?> element : tuple.getElements()) {
	                resultMap.put(element.getAlias(), tuple.get(element));
	            }
	            resultMapList.add(resultMap);
	        }

	        return resultMapList;
	    }	
}
