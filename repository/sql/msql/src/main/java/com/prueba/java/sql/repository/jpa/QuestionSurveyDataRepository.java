package com.prueba.java.sql.repository.jpa;

import com.prueba.java.sql.repository.entity.QuestionSurveyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface QuestionSurveyDataRepository extends CrudRepository<QuestionSurveyEntity,Integer> {

    List<QuestionSurveyEntity> findAllByQuestionModuleId(int id);
}
