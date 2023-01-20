package com.xdesign.hackronym.db;

import java.util.List;

import javax.transaction.Transactional;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xdesign.hackronym.domain.Acronym;

@Transactional
public interface AcronymRepository extends JpaRepository<Acronym, Integer> {

	@NotNull
	Acronym getById( @NotNull Integer id );

	Acronym getByAcronym( String acronym );

	@NotNull
	@Override
	List<Acronym> findAll();
}
