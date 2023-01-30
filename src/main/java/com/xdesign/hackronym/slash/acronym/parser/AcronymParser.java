package com.xdesign.hackronym.slash.acronym.parser;

import lombok.NonNull;
import org.springframework.stereotype.Component;

import com.xdesign.hackronym.domain.Acronym;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AcronymParser {
	private static final Pattern PSV_RX = Pattern.compile("^(?<acronym>[^|]+)\\|(?<meaning>[^|]+)\\|(?<description>[^|]+)$");
	private static final Pattern CSV_RX = Pattern.compile("^(?<acronym>[^,]+),(?<meaning>[^,]+),(?<description>.+)$");

	public @NonNull Optional<Acronym> parse(final @NonNull String message) {
		return parsePSV(message)
				.or(() -> parseCSV(message));
	}

	private Optional<Acronym> parsePSV(final String message ) {
		return attemptParseAs(PSV_RX, message);
	}

	private Optional<Acronym> parseCSV(final String message) {
		return attemptParseAs(CSV_RX, message);
	}

	private Optional<Acronym> attemptParseAs(final Pattern pattern, final String message) {
		return Optional.ofNullable(message)
				.map(pattern::matcher)
				.filter(Matcher::matches)
				.map(m -> Acronym.builder()
						.acronym(m.group("acronym"))
						.meaning(m.group("meaning"))
						.description(m.group("description"))
				)
				.map(Acronym.AcronymBuilder::build);
	}
}
