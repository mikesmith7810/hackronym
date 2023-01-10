package com.xdesign.hackronym.demonstrators.functionalinterface;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class SupplierDemonstratorTest {

	private SupplierDemonstrator supplierDemonstrator;

	@BeforeAll
	public void setup() {
		supplierDemonstrator = new SupplierDemonstrator();
	}

	@Test
	public void shouldPrintSomeOutputToConsole() {
		final String date = supplierDemonstrator.runExampleFor();

		assertThat( date ).isEqualTo( LocalDate.now().format( DateTimeFormatter.ISO_LOCAL_DATE ) );
	}
}
