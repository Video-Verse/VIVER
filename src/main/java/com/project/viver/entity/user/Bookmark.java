package com.project.viver.entity.user;

import com.project.viver.entity.common.BaseEntity;
import com.project.viver.entity.kopis.Musical;
import com.project.viver.entity.tmdb.Movie;
import com.project.viver.entity.tmdb.Tv;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name = "TB_BOOKMARK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends BaseEntity {

	private static final long serialVersionUID = -6371213137197729389L;

	@Id
    private String bookmarkId;
    
    private String contentId;
    private String userId;
    
    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "tvId")
    private Tv tv;

    @ManyToOne
    @JoinColumn(name = "mvId")
    private Musical musical;

}