package com.xdesign.hackronym.retriever;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.xdesign.hackronym.db.AcronymRepository;
import com.xdesign.hackronym.domain.Acronym;

@Component
public class AcronymRetriever {
	private final AcronymRepository acronymRepository;

	public AcronymRetriever( final AcronymRepository acronymRepository ) {
		this.acronymRepository = acronymRepository;
	}

	public String getAcronym( final String acronym ) {
		final Optional<Acronym> result = Optional
				.ofNullable( acronymRepository.getByAcronym( acronym ) );

		if ( result.isEmpty() ) {
			return "No acronym found for " + "*" + acronym + "*" + " - you can add a new one though using /addacronym";
		} else
			return result.get().toString();
	}

	public List<Acronym> getAll() {
		return acronymRepository.findAll();
	}
}
