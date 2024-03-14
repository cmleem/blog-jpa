package com.estsoft.blogjpa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.dto.ArticleResponse;
import com.estsoft.blogjpa.model.Article;
import com.estsoft.blogjpa.service.BlogService;

@RestController
public class BlogController {
	private final BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@PostMapping("/api/articles")
	public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
		Article article = blogService.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(article);
	}

	@RequestMapping(value = "/api/articles", method = RequestMethod.GET) // = @GetMapping
	public ResponseEntity<List<ArticleResponse>> showArticle() {
		List<Article> articleList = blogService.findAll();
		List<ArticleResponse> responseList = articleList.stream().map(x -> new ArticleResponse(x)).toList();
		return ResponseEntity.ok(responseList);
	}

	@GetMapping("/api/articles/{id}")
	public ResponseEntity<ArticleResponse> showOneArticle(@PathVariable Long id) {
		Article article = blogService.findById(id); // 500 error -> 404
		return ResponseEntity.ok(article.toResponse());
	}

	@DeleteMapping("api/articles/{id}")
	public ResponseEntity<Void> deleteOneArticle(@PathVariable Long id) {
		blogService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("api/articles/{id}")
	public ResponseEntity<Article> updateOneArticle(@PathVariable Long id, @RequestBody AddArticleRequest request){
		//Article updated = blogService.update(id, request);
		Article updated = blogService.updateTitle(id, request);
		return ResponseEntity.ok(updated);
	}
}
