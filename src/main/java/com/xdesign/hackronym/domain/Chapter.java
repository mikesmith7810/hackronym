package com.xdesign.hackronym.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Chapter {
	private final String name;
	private List<Example> examples;
}
