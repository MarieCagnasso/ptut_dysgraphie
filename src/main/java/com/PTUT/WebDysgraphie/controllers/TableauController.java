package com.PTUT.WebDysgraphie.controllers;

import com.PTUT.WebDysgraphie.models.Point;
import com.PTUT.WebDysgraphie.models.Tableau;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/tableau")
public class TableauController {

    private ObjectMapper objectMapper = new ObjectMapper();

    
        
    
    @GetMapping
    public String getBhktextePage() {
        return "bhktexte";
    }

    @PostMapping(consumes = "application/json", path = "/add")
    public String recupResult(
            @RequestBody() String body
    ) {
        JsonNode jsonNode;
        try{
        jsonNode = objectMapper.readTree(body);
        String nomPatient=jsonNode.path("nomPatient").asText();
        String prenomPatient=jsonNode.path("prenomPatient").asText();
        String sexe=jsonNode.path("sexe").asText();
        String niveau=jsonNode.path("niveau").asText();
        
        String bodyListePoint=jsonNode.path("liste_point").toString();
        
        ArrayList<Point> list_point =objectMapper.readValue(bodyListePoint,objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Point.class));
         
        String fileName = nomPatient+"_"+prenomPatient+"_"+sexe+"_"+niveau;
        new Tableau(fileName, fileName, list_point);
        }catch(Exception e){
            System.out.println(e);
        }
        

        return "bhktexte";
    }
}
