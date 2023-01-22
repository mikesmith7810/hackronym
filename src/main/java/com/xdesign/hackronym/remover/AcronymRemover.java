package com.xdesign.hackronym.remover;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.xdesign.hackronym.db.AcronymRepository;
import com.xdesign.hackronym.domain.Acronym;

@Component
public class AcronymRemover {
	private final AcronymRepository acronymRepository;

	public AcronymRemover( final AcronymRepository acronymRepository ) {
		this.acronymRepository = acronymRepository;
	}

	public String removeAcronym( final String acronym ) {

		final Optional<Acronym> result = Optional
				.ofNullable( acronymRepository.getByAcronym( acronym ) );

		if ( result.isEmpty() ) {
			return "No acronym found - you can add a new one though using /addacronym";
		} else {
			acronymRepository.delete( result.get() );
			return "Removed : " + acronym;
		}
	}
}
