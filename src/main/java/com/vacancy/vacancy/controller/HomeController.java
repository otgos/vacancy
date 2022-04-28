package com.vacancy.vacancy.controller;

import com.vacancy.vacancy.auth.AuthUserConfig;
import com.vacancy.vacancy.entity.Vacancy;
import com.vacancy.vacancy.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController extends AuthUserConfig {
    @Autowired
    VacancyService vacancyService;

    @GetMapping("/")
    public String homePage(Model model){
        List<Vacancy> vacancies = vacancyService.allVacancies();
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("currentUser", getCurrentUser());
        return "home";
    }

}
