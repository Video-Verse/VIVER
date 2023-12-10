package com.project.viver.entity.user;

import com.project.viver.entity.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    


}