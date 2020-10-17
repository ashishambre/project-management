package com.ashish.pma.controllers;

import com.ashish.pma.dao.EmployeeRepository;
import com.ashish.pma.dao.ProjectRepository;
import com.ashish.pma.dto.ChartData;
import com.ashish.pma.dto.EmployeeProject;
import com.ashish.pma.entities.Employee;
import com.ashish.pma.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        Map<String, Object> map = new HashMap<>();

        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);

        List<ChartData> projectData = projectRepository.getProjectStatus();

        // Converting projectData in JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        // ["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]

        model.addAttribute("projectStatusCount", jsonString);


        List<EmployeeProject> employeesProjectsCount = employeeRepository.employeeProjects();
        model.addAttribute("employeesProjectsCount", employeesProjectsCount);
        return "main/home";
    }
}
