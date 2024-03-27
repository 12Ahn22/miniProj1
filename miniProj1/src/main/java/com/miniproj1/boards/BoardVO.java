package com.miniproj1.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private Integer bno;
	private String title;
	private String author;
	private String content;
	private String createdAt;
	private Integer viewCount;
	// 요청 처리를 위한 필드
	private String action;
}
