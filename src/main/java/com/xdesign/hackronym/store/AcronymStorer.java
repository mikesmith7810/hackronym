package com.xdesign.hackronym.store;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.xdesign.hackronym.db.AcronymRepository;
import com.xdesign.hackronym.domain.Acronym;
import com.xdesign.hackronym.retriever.AcronymRetriever;
import com.xdesign.hackronym.slash.acronym.parser.AcronymParser;

@Component
@RequiredArgsConstructor
public class AcronymStorer {
	private final @NonNull AcronymRepository acronymRepository;

	private final @NonNull AcronymParser acronymParser;

	private final @NonNull AcronymRetriever acronymRetriever;

	public @NonNull String storeAcronym( final @NonNull String newAcronym ) {
		return acronymParser.parse( newAcronym )
				.map(this::attemptStore)
				.orElse("Supplied acronym not valid. Example is : /addacronym ASAP,As soon as possible,Its pretty quick!");
	}

	private @NonNull String attemptStore(final Acronym acronymToBeStored) {
		if ( acronymRetriever.getAcronym( acronymToBeStored.getAcronym() )
				.startsWith( acronymToBeStored.getAcronym() ) ) {
			return "Acronym already exists! You can remove the existing one using /removeacronym " + acronymToBeStored
					.getAcronym();
		}

		final Acronym acronym = acronymRepository.save( acronymToBeStored );

		return "Stored : " + acronym;
	}
}
