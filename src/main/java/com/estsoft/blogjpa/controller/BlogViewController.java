package com.estsoft.blogjpa.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.blogjpa.dto.ArticleListViewResponse;
import com.estsoft.blogjpa.dto.ArticleViewResponse;
import com.estsoft.blogjpa.model.Article;
import com.estsoft.blogjpa.service.BlogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BlogViewController {
	private final BlogService blogService;

	public BlogViewController(BlogService blogService) {
		this.blogService = blogService;
	}

	@GetMapping("/articles")
	public String getArticles(Model model) {
		List<ArticleListViewResponse> articles = blogService.findAll()
			.stream()
			.map(ArticleListViewResponse::new)
			.toList();
		model.addAttribute("articles", articles);
		return "articleList";
	}

	@GetMapping("/articles/{id}")
	public String showArticle(@PathVariable Long id, Model model) {
		Article article = blogService.findById(id);
		model.addAttribute("article", new ArticleViewResponse(article));

		return "article";
	}

	@GetMapping("/new-articles")  // /new-articles?id={id} -> 수정,  /new-articles -> 등록
	public String newArticle(@RequestParam(required = false) Long id, Model model) {
		log.info("id={}", id);
		if (id == null) {  // 등록 로직
			model.addAttribute("article", new ArticleViewResponse());
		} else{  // 수정 로직
			Article article = blogService.findById(id);
			model.addAttribute("article", new ArticleViewResponse(article));
		}
		return "newArticle";
	}
}
