package com.xdesign.hackronym.store;

import org.springframework.stereotype.Component;

import com.xdesign.hackronym.db.AcronymRepository;
import com.xdesign.hackronym.domain.Acronym;

@Component
public class AcronymStorer {
	private final AcronymRepository acronymRepository;

	public AcronymStorer( final AcronymRepository acronymRepository ) {
		this.acronymRepository = acronymRepository;
	}

	public Acronym storeAcronym( final Acronym acronym ) {
		return acronymRepository.save( acronym );
	}
}
