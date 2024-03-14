package com.estsoft.blogjpa.dto;

import java.time.LocalDateTime;

import com.estsoft.blogjpa.model.Article;

import lombok.Getter;

@Getter
public class ArticleViewResponse {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;

	public ArticleViewResponse() {
	}

	public ArticleViewResponse(Article article) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.createdAt = article.getCreatedAt();
	}
}
