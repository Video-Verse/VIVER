package com.project.viver.entity;

import com.project.viver.entity.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_img" )
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Img extends BaseEntity{

	@Id
	private String imgId;
	private String imgLoc;
	private String imgNm;
	private String imgExt;
	private Long imgSize;
	private String tempLoc;
	private String tempNm;
	private Long orderNo;
}