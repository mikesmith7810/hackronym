package com.xdesign.hackronym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.hackronym.task.Task;
import com.xdesign.hackronym.task.TaskResult;
import com.xdesign.hackronym.teachers.OptionalTeacher;

@RestController
public class OptionalController {

	@Autowired
	private OptionalTeacher optionalTeacher;

	@GetMapping("/java/optional")
	public TaskResult runLearningMaterial( @RequestBody final Task task ) {
		return optionalTeacher.teachThis( task );
	}
}
