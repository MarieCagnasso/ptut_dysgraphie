package com.PTUT.WebDysgraphie.controllers;

import com.PTUT.WebDysgraphie.models.Evaluation;
import com.PTUT.WebDysgraphie.models.Evaluation.typeMateriel;
import com.PTUT.WebDysgraphie.models.Evaluation.typeTest;
import com.PTUT.WebDysgraphie.models.Lecteur;
import com.PTUT.WebDysgraphie.models.Patient;
import com.PTUT.WebDysgraphie.models.Point;
import com.PTUT.WebDysgraphie.models.Tableau;
import com.PTUT.WebDysgraphie.models.Trace;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "/")
public class DysController {

    private ArrayList<Point> listPoint = new ArrayList<Point>(); // Liste des points à tracer.
    private ArrayList<Double> listPression = new ArrayList<Double>();
    private Tableau tableau;
    private long tempsDebut; // Temps de debut
    private long tempsPrecedent;
    private String sexe;
    private String niveau;
    private long tempsTotal;
    private Patient patient;
    private Evaluation monEvalution= new Evaluation(patient,typeTest.BHK, typeMateriel.graphique);


    @RequestMapping("/init")
    public String init(){
        this.tempsDebut = System.currentTimeMillis();
        this.tempsPrecedent = 0;
        return "fragments/page";
    }

    @RequestMapping("/erase")
    public String effacer(){
        this.listPression.clear();
        this.listPoint.clear();
        return "fragments/page";
    }

    @RequestMapping("/write")
    public String write(Model model) {
        if(sexe == null || niveau ==null){
            return "infos";
        }
        model.addAttribute("distance","");
        model.addAttribute("pression","");
        model.addAttribute("acceleration","");
        model.addAttribute("crayon","");
        this.listPoint.clear();
        this.listPression.clear();
        return "fragments/page";
    }

    

    @GetMapping("/results")
    public String result(@RequestParam long tempsE,Model model) throws IOException {
        Trace trace = new Trace(this.listPoint);
        Lecteur excelmodel = new Lecteur();
        ArrayList<Object> listvaleur = excelmodel.lire("./Modele.csv",sexe,niveau);
        this.tempsTotal = System.currentTimeMillis() - this.tempsDebut;

        //distance parcourue
        model.addAttribute("distance", excelmodel.analyzeDistance(listvaleur,this.listPoint));

        //pression
        model.addAttribute("pression",excelmodel.analyzePressure(listvaleur,this.listPression));

        //nb pics accélération/deceleration
        model.addAttribute("acceleration",excelmodel.analyzeAcceleration(listvaleur,this.listPoint));

        //rapport temps crayon en l'air/crayon sur la feuille
        model.addAttribute("crayon",excelmodel.analyzeTaTf(listvaleur,tempsE,this.tempsTotal));
        return "fragments/page::#details";
    }


    /*@RequestMapping("/saveInfos")
    public String saveInfos(@RequestParam String sexe, @RequestParam String niveau){
        this.sexe = sexe;
        this.niveau = niveau;
        return "fragments/show";
    }
*/

    @PostMapping("/addPoint")
    public String add(@RequestParam int pointX, @RequestParam int pointY) {
        long time = System.currentTimeMillis() - this.tempsDebut;
        long intervalle = 0;
        if(this.listPoint.size()!=0) intervalle = time - this.tempsPrecedent;
        Point point = new Point(pointX,pointY,this.listPoint.size(),(int)intervalle,(int)time);
        this.listPoint.add(point);
        this.tempsPrecedent = time;
        return "fragments/page";
    }

    @PostMapping("/addPression")
    public String addPressure(@RequestParam double pression){
        this.listPression.add(pression);
        return "fragments/page";
    }

    @RequestMapping("/download")
    public String download() {
       // this.tableau = new Tableau("fichier-"+System.currentTimeMillis()+".csv", this.listPoint, this.listPression,this.sexe,this.niveau);
        return "fragments/page";
    }
        
    @RequestMapping("/saveTestChoice")
    public String saveTestChoice(@RequestParam typeTest typetest){
        this.monEvalution.setTypetest(typetest);
        return "materiel";
    }
    
    @RequestMapping("/saveMaterielChoice")
    public String saveMaterielChoice(@RequestParam typeMateriel typemateriel){
        this.monEvalution.setTypemateriel(typemateriel);
        
        return "autorisation";
    }
    
    @RequestMapping("/saveInfosPatient")
    public String saveInfosPatient(){
        if(this.monEvalution.getTypetest()==typeTest.BHK){
            return "consignesBHK";
        }
        if(this.monEvalution.getTypetest()==typeTest.BHKADO){
            return "consignesBHKADO";
        }
        if(this.monEvalution.getTypetest()==typeTest.pangramme){
            return "consignesPangramme";
        }
        return null;
        
    }
    
    


}
