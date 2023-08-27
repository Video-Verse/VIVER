package com.project.viver.entity.reg;

import com.project.viver.entity.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_reg_contents" )
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegContents extends BaseEntity{

	@Id
	private String regId;
	private String typeId;
	private String contentId;
	private Long rating;
	private String reviewComment;
	private String viewingDate;
}
