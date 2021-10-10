package com.prueba.java.sql.repository.jpa;

import com.prueba.java.sql.repository.entity.QuestionModuleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionModuleDataRepository extends CrudRepository<QuestionModuleEntity,Integer> {
}
