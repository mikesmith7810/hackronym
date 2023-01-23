package com.xdesign.hackronym.reactions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class AcronymScanner {
    public List<String> scanForAcronyms(final String message) {
        final List<String> acronyms = new ArrayList<>();

        final Pattern pattern = Pattern.compile("[A-Z][A-Z]+");
        final Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            acronyms.add(matcher.group());
        }
        return acronyms;
    }
}
