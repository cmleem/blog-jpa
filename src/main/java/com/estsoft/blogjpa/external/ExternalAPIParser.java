package com.estsoft.blogjpa.external;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.service.BlogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExternalAPIParser {
	private final BlogService blogService;

	public ExternalAPIParser(BlogService blogService) {
		this.blogService = blogService;
	}

	//외부 api 호출 -> json 받아오기
	public void parserAndSave() {
		String url = "https://jsonplaceholder.typicode.com/postsv";

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			log.info("body: {}", response.getBody());
			List<LinkedHashMap<String, Object>> list = response.getBody();
			List<AddArticleRequest> insertList = new ArrayList<>();

			for (LinkedHashMap<String, Object> map : list) {
				String title = (String) map.get("title");
				String content = (String) map.get("body");

				blogService.save(new AddArticleRequest(title, content));
			}
		}
	}
}
