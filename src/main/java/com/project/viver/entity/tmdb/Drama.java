package com.project.viver.entity.tmdb;

import java.util.Map;

import com.project.viver.entity.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name="tb_drama")
public class Drama extends BaseEntity {

	private static final long serialVersionUID = -3927574973426688826L;

	@Id
    private String dramaId;

    private Long   id;
    private String name;
    private String originalLanguage;
    private String originalName;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private Float  voteAverage;
    private Long   voteCount;
    private String firstAirDate;
    private Float  popularity;
}