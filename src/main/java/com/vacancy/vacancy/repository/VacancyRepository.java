package com.vacancy.vacancy.repository;

import com.vacancy.vacancy.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByVacancyNameContainingIgnoreCase (String keyword);
}
