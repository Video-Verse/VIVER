package com.project.viver.entity.tmdb;

import java.util.Map;
import java.util.Set;

import com.project.viver.entity.common.BaseEntity;
import com.project.viver.entity.user.Bookmark;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_movie")
public class Movie extends BaseEntity {

	private static final long serialVersionUID = -3927574973426688826L;

	@Id
    private String movieId;

    private Long id;
    private String title;
    private String adult;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private Float  voteAverage;
    private Long   voteCount;
    private String releaseDate;
    private Float  popularity;

	public Movie(Map param) {
		this.id = Long.parseLong(param.get("id").toString());
		this.title = param.get("title").toString();
		this.adult = param.get("adult").toString();
		this.originalLanguage = param.get("originalLanguage").toString();
		this.originalTitle = param.get("originalTitle").toString();
		this.overview = param.get("overview").toString();
		this.posterPath = param.get("posterPath").toString();
		this.backdropPath = param.get("backdropPath").toString();
		this.voteAverage = Float.parseFloat(param.get("voteAverage").toString());
		this.voteCount = Long.parseLong(param.get("voteCount").toString());
		this.releaseDate = param.get("releaseDate").toString();;
		this.popularity = Float.parseFloat(param.get("popularity").toString());
	}

	// 수정시 데이터 처리
    public Movie setUpdate(String title, String adult, String originalLanguage, String originalTitle, String overview, String posterPath, String backdropPath, Float voteAverage, Long voteCount, String releaseDate, Float popularity) {
		this.title = title;
		this.adult = adult;
		this.originalLanguage = originalLanguage;
		this.originalTitle = originalTitle;
		this.overview = overview;
		this.posterPath = posterPath;
		this.backdropPath = backdropPath;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
		this.releaseDate = releaseDate;
		this.popularity = popularity;
        return this;
    }

}