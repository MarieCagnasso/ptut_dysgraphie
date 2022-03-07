package com.PTUT.WebDysgraphie.controllers;

import com.PTUT.WebDysgraphie.models.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/patient")
public class PatientController {
    /**
     * Affiche le formulaire informations patient, méthode GET
     *
     * @param patient modèle de donnée pour le formulaire
     * @return infosPatient.html
     */
    @GetMapping("add")
    public String getAddPatient(@ModelAttribute("patient") Patient patient, Model model){
        model.addAttribute("patient",patient);
        model.addAttribute("classes",Patient.classes.values());
        return "infosPatient";
    }
}
