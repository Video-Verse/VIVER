package com.project.viver.entity.kopis;

import com.project.viver.entity.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="tb_musical")
public class Musical extends BaseEntity{
	private static final long serialVersionUID = 5253502225379611548L;
	@Id
	 private String mvId;
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
	 private String pcseguidance;
	 private String sty;
	 private String dtguidance;

}
