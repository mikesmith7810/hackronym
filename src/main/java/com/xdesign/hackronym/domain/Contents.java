package com.xdesign.hackronym.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class Contents {
	private List<Chapter> chapters;

}
