package com.miniproj1.boards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	private Integer bno;
	private String title;
	private String author;
	private String content;
	private String createdAt;
	private Integer viewCount;
	// 요청 처리를 위한 필드
	private String action;

	public BoardVO(Integer bno, String title, String author, String content, String createdAt, Integer viewCount) {
		this(bno, title, author, content, createdAt, viewCount, null);
	}
}
