package com.vacancy.vacancy.service;

import com.vacancy.vacancy.entity.ApplicationForm;
import com.vacancy.vacancy.repository.ApplicationFormRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationFormService {
    private final ApplicationFormRepo applicationFormRepo;

    public ApplicationForm saveForm(ApplicationForm applicationForm){
        return applicationFormRepo.save(applicationForm);
    }
    public List<ApplicationForm> allAppliedList(){
        return applicationFormRepo.findAll();
    }
    public ApplicationForm findById(Long id){
        return applicationFormRepo.getById(id);
    }
}
