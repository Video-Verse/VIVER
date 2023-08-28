package com.project.viver.common.util;

import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.PageRequest;

public class CommonUtil {
	
	private CommonUtil() {
		throw new IllegalStateException("CommonUtil class");
	}
	
	public static PageRequest PageRequest(Map<String, String> params) {
		return PageRequest.of(NumberUtils.toInt(params.get("page"), 0), NumberUtils.toInt(params.get("size"), 10));
	}
}
