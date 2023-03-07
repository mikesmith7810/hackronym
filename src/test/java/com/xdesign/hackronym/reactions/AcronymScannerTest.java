package com.xdesign.hackronym.reactions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AcronymScannerTest {
	private AcronymScanner acronymScanner;

	@BeforeEach
	void setup() {
		this.acronymScanner = new AcronymScanner();
	}

	@Test
	void shouldFindNoAcronymsInEmptyString() {
		assertThat(acronymScanner.scanForAcronyms(""))
				.isEmpty();
	}

	@Test
	void shouldFindNoAcronymsInNonEmptyString() {
		assertThat(acronymScanner.scanForAcronyms("Nothing To See Here"))
				.isEmpty();
	}

	@Test
	void shouldFindOneAcronymAllAlone() {
		assertThat(acronymScanner.scanForAcronyms("WTF"))
				.containsExactly("WTF");
	}

	@Test
	void shouldFindOneAcronymAtStartOfText() {
		assertThat(acronymScanner.scanForAcronyms("WTF is this?"))
				.containsExactly("WTF");
	}

	@Test
	void shouldFindOneAcronymAtEndOfText() {
		assertThat(acronymScanner.scanForAcronyms("is this WTF"))
				.containsExactly("WTF");
	}

	@Test
	void shouldFindOneAcronymAtAmongstText() {
		assertThat(acronymScanner.scanForAcronyms("is this WTF is this?"))
				.containsExactly("WTF");
	}

	@Test
	void shouldFindTwoAcronymsTogether() {
		assertThat(acronymScanner.scanForAcronyms("is this WTF YOLO is this?"))
				.containsExactly("WTF", "YOLO");
	}

	@Test
	void shouldFindTwoAcronymsApart() {
		assertThat(acronymScanner.scanForAcronyms("is this WTF who does YOLO is this?"))
				.containsExactly("WTF", "YOLO");
	}
}
