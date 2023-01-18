package com.xdesign.hackronym.slash.acronym.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AcronymValidatorTest {
	AcronymValidator acronymValidator;

	@BeforeEach
	public void setup() {
		this.acronymValidator = new AcronymValidator();

	}

	@Test
	public void shouldReturnTrueIfAllThreeParamsSupplied() {
		assertThat( acronymValidator.isValid( "ASAP,as soon as possible,its pretty quick" ) )
				.isTrue();
	}

	@Test
	public void shouldReturnFalseIfAllThreeParamsAreNotSupplied() {
		assertThat( acronymValidator.isValid( "ASAP,its pretty quick" ) ).isFalse();
	}

	@Test
	public void shouldReturnFalseIfAllThreeParamsAreNotBlank() {
		assertThat( acronymValidator.isValid( "ASAP,its pretty quick," ) ).isFalse();
	}

	@Test
	public void shouldReturnFalseIfFirstParamIsNotAllUppercase() {
		assertThat( acronymValidator.isValid( "notuppercase,its pretty quick," ) ).isFalse();
	}
}
