package com.ensias.springbootinit.Controller;
import com.ensias.springbootinit.model.Module;

import com.ensias.springbootinit.model.Cours;
import com.ensias.springbootinit.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CoursController {
    @Autowired
    private CoursService coursService;
    @RequestMapping("/modules/{id}/cours")
    public List<Cours> getAllCours(@PathVariable Long id){
        return coursService.getAllCours(id);
    }
    @RequestMapping("/modules/{moduleId}/cours/{id}")
    public Optional<Cours> getCours(@PathVariable Integer id){
        return coursService.getCours(id);
    }
    @RequestMapping(method=RequestMethod.POST, value="/modules/{moduleId}/cours")
    public void ajouterCours(@RequestBody Cours cours, @PathVariable Integer moduleId) {
        cours.setModule(new Module(moduleId,"", ""));
        coursService.ajouterCours(cours);
    }
    @RequestMapping(method=RequestMethod.PUT, value="/modules/{moduleId}/cours/{id}")
    public void modifierCours(@RequestBody Cours cours, @PathVariable Integer moduleId,
                              @PathVariable Integer id) {
        cours.setModule(new Module(moduleId,"",""));
        coursService.modifierCours(cours);
    }
    @RequestMapping(method=RequestMethod.DELETE, value="/modules/{moduleId}/cours/{id}")
//pour supprimer un Cours donné d’un Module donné
    public void supprimerCours(@PathVariable Integer id) {
        coursService.supprimerCours(id);
    }
}