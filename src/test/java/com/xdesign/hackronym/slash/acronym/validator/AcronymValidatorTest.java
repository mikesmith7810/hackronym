package com.xdesign.hackronym.slash.acronym.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AcronymValidatorTest {
	AcronymValidator acronymValidator;

	@BeforeEach
	public void setup() {
		this.acronymValidator = new AcronymValidator();

	}

	@Test
	void shouldReturnTrueIfAllThreeParamsSupplied() {
		assertThat( acronymValidator.isValid( "ASAP,as soon as possible,its pretty quick" ) )
				.isTrue();
	}

	@Test
	void shouldReturnFalseIfAllThreeParamsAreNotSupplied() {
		assertThat( acronymValidator.isValid( "ASAP,its pretty quick" ) ).isFalse();
	}

	@Test
	void shouldReturnFalseIfAllThreeParamsAreNotBlank() {
		assertThat( acronymValidator.isValid( "ASAP,its pretty quick," ) ).isFalse();
	}

	@Test
	void shouldReturnFalseIfFirstParamIsNotAllUppercase() {
		assertThat( acronymValidator.isValid( "notuppercase,its pretty quick," ) ).isFalse();
	}
}
