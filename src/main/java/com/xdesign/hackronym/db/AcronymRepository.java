package com.xdesign.hackronym.db;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xdesign.hackronym.domain.Acronym;

@Transactional
public interface AcronymRepository extends JpaRepository<Acronym, Integer> {

	@NonNull
	Acronym getById( @NonNull Integer id );

	Optional<Acronym> getByAcronym( @NonNull String acronym );

	@NonNull
	@Override
	List<Acronym> findAll();
}
