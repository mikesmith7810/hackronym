package com.xdesign.hackronym.reactions;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.NonNull;

@Component
public class AcronymScanner {
	private static final Pattern PATTERN = Pattern.compile( "[A-Z][A-Z]+" );

	public @NonNull List<String> scanForAcronyms( final @NonNull String message ) {
        return PATTERN.matcher(message).results()
                .map(MatchResult::group)
                .collect(Collectors.toList());
    }
}
