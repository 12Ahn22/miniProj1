package com.miniproj1.utils;

import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
	public static Map<String, Object> convertMap(Map<String, String[]> map) {
		Map<String, Object> result = new HashMap<>();
		for (var entry : map.entrySet()) {
			if (entry.getValue().length == 1) {
				// 문자열 1건
				// 해당 키에 해당하는 값이 단일 객체라는 의미
				result.put(entry.getKey(), entry.getValue()[0]);
			} else {
				// 문자열 배열을 추가한다
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;
	}
}
