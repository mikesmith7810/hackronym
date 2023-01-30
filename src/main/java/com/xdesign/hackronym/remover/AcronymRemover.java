package com.xdesign.hackronym.remover;

import org.springframework.stereotype.Component;

import com.xdesign.hackronym.db.AcronymRepository;
import com.xdesign.hackronym.domain.Acronym;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AcronymRemover {
	private final @NonNull AcronymRepository acronymRepository;

	public @NonNull String removeAcronym(final @NonNull String acronym ) {
		return acronymRepository.getByAcronym(acronym)
				.map(this::handleDelete)
				.orElse("No acronym found - you can add a new one though using /addacronym");
	}

	private String handleDelete(final Acronym acronym) {
		acronymRepository.delete(acronym);
		return "Removed : " + acronym;
	}
}
