package com.ensias.springbootinit.Repository;

import java.util.List;

import com.ensias.springbootinit.model.Cours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends CrudRepository<Cours, Integer>{

    public List<Cours> findByModuleId(Long moduleId);
}
