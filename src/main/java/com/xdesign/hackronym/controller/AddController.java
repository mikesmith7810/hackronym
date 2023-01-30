package com.xdesign.hackronym.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.hackronym.domain.AcronymMessage;
import com.xdesign.hackronym.store.AcronymStorer;

@RestController
public class AddController {

	final AcronymStorer acronymStorer;

	public AddController( AcronymStorer acronymStorer ) {
		this.acronymStorer = acronymStorer;
	}

	@PostMapping("/addacronym")
	public String addAcronym( @RequestBody final AcronymMessage acronymMessage ) {
		return acronymStorer.storeAcronym( acronymMessage.getAcronymMessage() );
	}
}
