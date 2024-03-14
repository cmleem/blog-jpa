package com.estsoft.blogjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.estsoft.blogjpa.external.ExternalAPIParser;

@Controller
public class JsonParseTestController {

	private final ExternalAPIParser externalAPIParser;

	public JsonParseTestController(ExternalAPIParser externalAPIParser) {
		this.externalAPIParser = externalAPIParser;
	}

	@GetMapping("/client/test")
	public void test() {
		externalAPIParser.parserAndSave();
	}
}
