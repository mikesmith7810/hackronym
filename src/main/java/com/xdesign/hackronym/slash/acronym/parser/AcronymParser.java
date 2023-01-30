package com.xdesign.hackronym.slash.acronym.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Component;

import com.xdesign.hackronym.domain.Acronym;

import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.util.Objects;
import java.util.Optional;

@Component
public class AcronymParser {
	public Acronym parse( final String message ) {
		try {
			final var csv = new CSVReader(new StringReader(Objects.requireNonNull(message)));
			return Optional.ofNullable(csv.readNext())
					.map(fields -> Acronym.builder()
							.acronym(fields[0])
							.meaning(fields[1])
							.description(fields[2])
							.build())
					.orElseThrow(IllegalArgumentException::new);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (CsvValidationException e) {
			throw new IllegalArgumentException(String.format("Malformed input: %s", message), e);
		}
	}
}
