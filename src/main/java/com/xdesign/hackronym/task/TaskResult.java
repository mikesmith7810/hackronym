package com.xdesign.hackronym.task;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TaskResult {
	private final TaskType type;
	private final String value;
	private final String sourceCode;
	private final String description;

}
