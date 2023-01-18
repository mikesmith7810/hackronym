package com.xdesign.hackronym.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "acronyms")
@NoArgsConstructor
@AllArgsConstructor
public class Acronym {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "acronym")
	private String acronym;

	@Column(name = "meaning")
	private String meaning;

	@Column(name = "description")
	private String description;

	@Override
	public String toString() {
		return acronym + " : " + meaning + " - " + description + ".";
	}

}
