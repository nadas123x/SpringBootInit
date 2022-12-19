package com.ensias.springbootinit.services;

import com.ensias.springbootinit.Repository.ModuleRepository;
import com.ensias.springbootinit.model.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
   private ModuleRepository moduleRepository;

    public List<Module> getAllModules() {
        List<Module> modules = new ArrayList<>();
        moduleRepository.findAll().forEach(modules::add);
        return modules;
    }
    public void ajouterModule(Module module) {
        moduleRepository.save(module);
    }

    public void modifierModule(Integer id, Module module) {
        moduleRepository.save(module);
    }
    public void supprimerModule(Long id) {
        moduleRepository.deleteById(id);
    }


    public Optional<Module> getModule(Long id) {
        return moduleRepository.findById(id);
    }

            }


