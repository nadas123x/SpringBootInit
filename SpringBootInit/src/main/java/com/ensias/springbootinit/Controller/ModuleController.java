package com.ensias.springbootinit.Controller;

import com.ensias.springbootinit.Repository.ModuleRepository;
import com.ensias.springbootinit.model.Module;
import com.ensias.springbootinit.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")


@RestController
public class ModuleController {
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ModuleRepository moduleRepo;

    @RequestMapping("/modules")
    public List<Module> getModules() {
        return moduleService.getAllModules();
    }

    @RequestMapping("/modules/{id}")
    public Optional<Module> getModule(@PathVariable Long id){
        return moduleService.getModule(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/modules")
    public void ajouterModule(@RequestBody Module module) {
        moduleService.ajouterModule(module);
    }


    @GetMapping("/modules/{id}")
    public ResponseEntity<Module> getModuleById(@PathVariable("id") Long id) {
        System.out.println("here");
        return new ResponseEntity<Module>(moduleRepo.findById(id).get(), HttpStatus.OK);

    }
    @PutMapping("/modules/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable("id") Long id, @RequestBody Module module){
        Module b = moduleRepo.findById(id).get();
        if(b.getId()!=0) {
            b.setNom(module.getNom());
            b.setDescription(module.getDescription());

        }
        return new ResponseEntity<Module>(moduleRepo.save(b),HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/modules/{id}")
    public ResponseEntity<Object> supprimerModule(@PathVariable Long id) {
        moduleService.supprimerModule(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/items")
    public Page<Module> getAllItems(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        return moduleService.getAllItems(PageRequest.of(page, size));
    }


    @GetMapping("/search")
    public Page<Module> search(@RequestParam(required = false, defaultValue = "") String query, Pageable pageable) {
        if (query == null || query.isEmpty()) {
            return moduleRepo.findAll(pageable);
        } else {
            return moduleRepo.findByNomContaining(query, pageable);
        }
    }
}









