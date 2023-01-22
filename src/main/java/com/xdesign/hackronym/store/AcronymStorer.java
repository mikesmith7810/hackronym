package com.xdesign.hackronym.store;

import org.springframework.stereotype.Component;

import com.xdesign.hackronym.db.AcronymRepository;
import com.xdesign.hackronym.domain.Acronym;
import com.xdesign.hackronym.retriever.AcronymRetriever;
import com.xdesign.hackronym.slash.acronym.parser.AcronymParser;
import com.xdesign.hackronym.slash.acronym.validator.AcronymValidator;

@Component
public class AcronymStorer {
	private final AcronymRepository acronymRepository;

	final AcronymValidator acronymValidator;

	final AcronymParser acronymParser;

	final AcronymRetriever acronymRetriever;

	public AcronymStorer( final AcronymRepository acronymRepository,
			final AcronymValidator acronymValidator, final AcronymParser acronymParser,
			final AcronymRetriever acronymRetriever ) {
		this.acronymRepository = acronymRepository;
		this.acronymValidator = acronymValidator;
		this.acronymParser = acronymParser;
		this.acronymRetriever = acronymRetriever;
	}

	public String storeAcronym( final String newAcronym ) {

		if ( !acronymValidator.isValid( newAcronym ) ) {
			return "Supplied acronym not valid. Example is : /addacronym ASAP,As soon as possible,Its pretty quick!";
		}

		final Acronym acronymToBeStored = acronymParser.parse( newAcronym );

		if ( acronymRetriever.getAcronym( acronymToBeStored.getAcronym() )
				.startsWith( acronymToBeStored.getAcronym() ) ) {
			return "Acronym already exists! You can remove the existing one using /removeacronym " + acronymToBeStored
					.getAcronym();
		}

		final Acronym acronym = acronymRepository.save( acronymToBeStored );

		return "Stored : " + acronym;
	}
}
