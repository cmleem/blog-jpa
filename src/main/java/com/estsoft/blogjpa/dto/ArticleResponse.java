package com.estsoft.blogjpa.dto;

import com.estsoft.blogjpa.model.Article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleResponse {
	private String title;
	private String content;

	public ArticleResponse(Article article){
		title = article.getTitle();
		content = article.getContent();
	}
}
