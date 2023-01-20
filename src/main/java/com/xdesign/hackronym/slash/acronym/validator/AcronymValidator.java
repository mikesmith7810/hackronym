package com.xdesign.hackronym.slash.acronym.validator;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class AcronymValidator {
	public boolean isValid( final String message ) {
		final String[] params = message.split( "," );

		return !allParamsAreNotSupplied( params ) && !aParamIsBlank(
				params ) && !firstParamIsNotUpperCase( params[0] );
	}

	private boolean firstParamIsNotUpperCase( final String param ) {
		return !param.toUpperCase().equals( param );
	}

	private boolean aParamIsBlank( final String[] params ) {
		final int size = (int) Arrays.stream( params )
				.filter( param -> param.length() > 0 )
				.count();
		return size != 3;

	}

	private boolean allParamsAreNotSupplied( final String[] params ) {
		return params.length != 3;
	}
}
