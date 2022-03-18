package com.PTUT.WebDysgraphie.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Johana Dahan
 */
@Controller
@RequestMapping(path = "/")
public class AutorisationController {
    
    @RequestMapping("/infosPatient")
    public String materiel(){ return "infosPatient";}
    
}