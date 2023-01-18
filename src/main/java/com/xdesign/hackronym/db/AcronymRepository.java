package com.xdesign.hackronym.db;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xdesign.hackronym.domain.Acronym;

@Transactional
public interface AcronymRepository extends JpaRepository<Acronym, Integer> {

	Acronym getById( Integer id );

	Acronym getByAcronym( String acronym );

	@Override
	List<Acronym> findAll();
}
