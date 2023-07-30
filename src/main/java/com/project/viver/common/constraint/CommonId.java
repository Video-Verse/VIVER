package com.project.viver.common.constraint;

public enum CommonId {

	  USER("U")            //회원
	, LEVEL("L")           //레벨
	, BOOKMARK("K")        //보관
	, REG_CONTENTS("R")    //등록작품
	, MOVIE("M")           //영화
	, DRAMA("D")           //드라마
	, MUSICAL("M")         //뮤지컬
	, TYPE("Y")            //작품타입
	, IMG("I")             //이미지
	, BANNER("B")          //배너
	, BATCH("H")           //배치
	;

	private String value;

	CommonId(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
