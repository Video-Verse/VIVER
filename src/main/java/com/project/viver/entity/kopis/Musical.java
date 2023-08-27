package com.project.viver.entity.kopis;

import com.project.viver.entity.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Musical extends BaseEntity{

	@Id
	 private String mv_id;
	 private String mt20id;
	 private String prfnm;
	 private String genrenm;
	 private String prfstate;
	 private String prfpdfrom;
	 private String prfpdto;
	 private String poster;
	 private String fcitynm;
	 private String openrun;
	 private String prfcast;
	 private String prfcrew;
	 private String prfruntime;
	 private String prfage;
	 private String entrpsnm;
	 private String sty;
	 private String dtguidance;

}
