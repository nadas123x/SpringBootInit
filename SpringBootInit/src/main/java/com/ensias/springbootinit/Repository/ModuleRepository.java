package com.ensias.springbootinit.Repository;


import com.ensias.springbootinit.model.Module;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Long> {
}