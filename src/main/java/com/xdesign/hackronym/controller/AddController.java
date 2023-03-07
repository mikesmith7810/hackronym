package com.xdesign.hackronym.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.hackronym.domain.AcronymMessage;
import com.xdesign.hackronym.store.AcronymStorer;

@RestController
@RequiredArgsConstructor
public class AddController {

	private final AcronymStorer acronymStorer;

	@PostMapping("/addacronym")
	public String addAcronym( @RequestBody final @NonNull AcronymMessage acronymMessage ) {
		return acronymStorer.storeAcronym( acronymMessage.getAcronymMessage() );
	}
}
