package com.project.viver.common.constraint;

public enum CommonId {

	  USER("US")            //회원
	, LEVEL("LV")           //레벨
	, BOOKMARK("BM")        //보관
	, REG_CONTENTS("RC")    //등록작품
	, MOVIE("MV")           //영화
	, TV("TV")              //TV
	, MUSICAL("MS")         //뮤지컬
	, IMG("IM")             //이미지
	, BANNER("BN")          //배너
	, BATCH("BT")           //배치
	, ALL("ALL")           //배치
	;

	private String value;

	CommonId(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
