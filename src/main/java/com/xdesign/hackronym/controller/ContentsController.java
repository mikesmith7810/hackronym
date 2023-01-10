package com.xdesign.hackronym.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.hackronym.contents.ContentsStore;
import com.xdesign.hackronym.domain.Contents;

@RestController
public class ContentsController {

	private ContentsStore contentsStore;

	public ContentsController( final ContentsStore contentsStore ) {
		this.contentsStore = contentsStore;
	}

	@GetMapping("/java/contents")
	public Contents getContents() {
		return contentsStore.retrieveContents();
	}
}
