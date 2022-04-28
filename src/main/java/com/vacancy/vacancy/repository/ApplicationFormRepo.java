package com.vacancy.vacancy.repository;

import com.vacancy.vacancy.entity.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationFormRepo extends JpaRepository<ApplicationForm, Long> {
}
