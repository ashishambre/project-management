package com.ashish.pma.controllers;

import com.ashish.pma.dao.EmployeeRepository;
import com.ashish.pma.dao.ProjectRepository;
import com.ashish.pma.entities.Employee;
import com.ashish.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        Project project = new Project();
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("project", project);
        model.addAttribute("employees", employees);
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project, Model model) {
        projectRepository.save(project);
        // redirect to prevent duplicate submissions
        return "redirect:/projects/new";
    }
}
