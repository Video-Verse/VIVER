package com.project.viver.entity.tmdb;

import com.project.viver.entity.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie extends BaseEntity {

    @Id
    private String id;

    private String title;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private Float  voteAverage;
    private Long   voteCount;
    private String releaseDate;
    private Float  popularity;


}