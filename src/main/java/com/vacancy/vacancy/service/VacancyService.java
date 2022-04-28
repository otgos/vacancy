package com.vacancy.vacancy.service;

import com.vacancy.vacancy.entity.Vacancy;
import com.vacancy.vacancy.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;

    public Vacancy addVacancy(Vacancy vacancy){
        return vacancyRepository.save(vacancy);
    }

    public Vacancy getVacancyById(Long id){
        return vacancyRepository.getById(id);
    }
    public List<Vacancy> allVacancies(){
        return vacancyRepository.findAll();
    }
    public List<Vacancy> searchByVacancyName( String keyword){
       return vacancyRepository.findByVacancyNameContainingIgnoreCase(keyword);
    }
}
