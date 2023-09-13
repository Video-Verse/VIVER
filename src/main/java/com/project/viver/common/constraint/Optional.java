package com.project.viver.common.constraint;

public enum Optional {
	Yes("Y"), No("N");

	private String value;

	Optional(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}