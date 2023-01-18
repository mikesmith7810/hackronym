package com.xdesign.hackronym.retriever;

import org.springframework.stereotype.Component;

import com.xdesign.hackronym.db.AcronymRepository;
import com.xdesign.hackronym.domain.Acronym;

@Component
public class AcronymRetriever {
    private final AcronymRepository acronymRepository;

    public AcronymRetriever(AcronymRepository acronymRepository) {
        this.acronymRepository = acronymRepository;
    }

    public Acronym getAcronym(final String acronym) {
        return acronymRepository.getByAcronym(acronym);
    }
}
