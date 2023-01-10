package com.xdesign.hackronym.task;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {
	final private TaskType taskType;
	final private List<String> parameters;
}
