package com.xdesign.hackronym.slash.acronym.parser;

import org.springframework.stereotype.Component;

import com.xdesign.hackronym.domain.Acronym;

@Component
public class AcronymParser {
	public Acronym parse( final String message ) {
		final String[] params = message.split( "," );
		return Acronym.builder()
				.acronym( params[0] )
				.meaning( params[1] )
				.description( params[2] )
				.build();
	}
}
