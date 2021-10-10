package com.prueba.java.sql.repository.jpa;

import com.prueba.java.sql.repository.entity.AnswerSurveyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerSurveyDataRepository extends CrudRepository<AnswerSurveyEntity,Integer> {
}
