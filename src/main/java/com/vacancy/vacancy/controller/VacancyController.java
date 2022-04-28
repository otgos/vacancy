package com.vacancy.vacancy.controller;

import com.vacancy.vacancy.auth.AuthUserConfig;
import com.vacancy.vacancy.entity.ApplicationForm;
import com.vacancy.vacancy.entity.User;
import com.vacancy.vacancy.entity.Vacancy;
import com.vacancy.vacancy.service.ApplicationFormService;
import com.vacancy.vacancy.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class VacancyController extends AuthUserConfig {
    @Autowired
    VacancyService vacancyService;
    @Autowired
    ApplicationFormService applicationFormService;

    @GetMapping("/vacancy/addVacancy")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String addVacancy(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "vacancy/addVacancy";
    }

    @PostMapping("/vacancy/addVacancy")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String addVacancy(@RequestParam("name") String vacName,
                             @RequestParam("description") String vacDescription,
                             @RequestParam("requirement") String vacRequirement,
                             @RequestParam("salary") String vacSalary, Model model){
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyName(vacName);
        vacancy.setVacancyDescription(vacDescription);
        vacancy.setVacancyRequirement(vacRequirement);
        vacancy.setSalary(vacSalary);
        vacancy.setPostDate(new Date());
        vacancyService.addVacancy(vacancy);

        model.addAttribute("currentUser", getCurrentUser());
        return "redirect:/";
    }

    @GetMapping("/vacancy/allVacancies")
    @PreAuthorize("isAuthenticated()")
    public String allVacancies(Model model) {
        List<Vacancy> vacancies = vacancyService.allVacancies();
        model.addAttribute("vacancies", vacancies);

        model.addAttribute("currentUser", getCurrentUser());
        return "vacancy/allVacancies";
    }
    @GetMapping("/vacancy/vacancyDetail/{id}")
    @PreAuthorize("isAuthenticated()")
    public String vacancyDetail(Model model, @PathVariable(name = "id") Long id) {
        Vacancy vacancy = vacancyService.getVacancyById(id);
        model.addAttribute("vacancy", vacancy);

        model.addAttribute("currentUser", getCurrentUser());
        return "vacancy/vacancyDetail";
    }

    @PostMapping("/vacancy/applyVacancy")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> applyVacancy(
            @RequestParam(name = "user_email") String email,
            @RequestParam(name = "profession") String profession,
            @RequestParam(name="phone") String phone,
            @RequestParam(name="vacId") Vacancy vacancy,
            @RequestParam(name="userId") User user,
            @RequestParam(name = "fullName") String fullName, Model model) {
        ApplicationForm applicationForm = new ApplicationForm();
        applicationForm.setEmail(email);
        applicationForm.setPhoneNumber(phone);
        applicationForm.setFullName(fullName);
        applicationForm.setApplyDate(new Date());
        applicationForm.setProfession(profession);
        applicationForm.setVacancy(vacancy);
        applicationForm.setUser(user);

        model.addAttribute("currentUser", getCurrentUser());

        if (applicationFormService.saveForm(applicationForm) != null) {
            return new ResponseEntity<>("Өтініш сәтті жіберілді", HttpStatus.OK);
        }
        return new ResponseEntity<>("Қате табылды!", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/vacancy/applyVacancy/{id}")
    @PreAuthorize("isAuthenticated()")
    public String applyVacancy(Model model, @PathVariable(name = "id") Long id){
        Vacancy vacancy = vacancyService.getVacancyById(id);
        model.addAttribute("vacId", vacancy);
        model.addAttribute("currentUser", getCurrentUser());
        return "vacancy/applyVacancy";
    }

    @GetMapping("/application/appliedList")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String appliedList(Model model) {
        List<ApplicationForm> applicationFormList = applicationFormService.allAppliedList();
        model.addAttribute("appliedList", applicationFormList);
        model.addAttribute("currentUser", getCurrentUser());
        return "/application/appliedList";
    }

    @GetMapping("/vacancy/vacancySearch")
    
    public String vacancySearch(@RequestParam(name="keyword") String keyword, Model model) {
        List<Vacancy> vacancies = vacancyService.searchByVacancyName(keyword);
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("currentUser", getCurrentUser());
        return "vacancy/vacancySearch";
    }

}
