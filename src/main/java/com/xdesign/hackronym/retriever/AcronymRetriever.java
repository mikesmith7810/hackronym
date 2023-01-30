package com.xdesign.hackronym.retriever;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xdesign.hackronym.db.AcronymRepository;
import com.xdesign.hackronym.domain.Acronym;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AcronymRetriever {
	private final @NonNull AcronymRepository acronymRepository;

	public @NonNull String getAcronym(final @NonNull String acronym ) {
		return acronymRepository.getByAcronym( acronym )
				.map(Acronym::toString)
				.orElse("No acronym found for " + "*" + acronym + "*" + " - you can add a new one though using /addacronym");
	}

	public List<Acronym> getAll() {
		return acronymRepository.findAll();
	}
}
