package com.xdesign.hackronym.retriever;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xdesign.hackronym.db.AcronymRepository;
import com.xdesign.hackronym.domain.Acronym;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AcronymRetrieverTest {

    private AcronymRetriever acronymRetriever;

    @Mock
    private AcronymRepository acronymRepository;

    @BeforeEach
	public void setup() {
        this.acronymRetriever = new AcronymRetriever(acronymRepository);
    }

    @Test
    public void shoudlCallOutToAcronymRepository(){
        Acronym acronym = acronymRetriever.getAcronym("ASAP");

        verify(acronymRepository).getByAcronym("ASAP");

    }
}