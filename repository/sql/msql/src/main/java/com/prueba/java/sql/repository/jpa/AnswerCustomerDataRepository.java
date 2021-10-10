package com.prueba.java.sql.repository.jpa;

import com.prueba.java.sql.repository.entity.AnswerCustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerCustomerDataRepository extends CrudRepository<AnswerCustomerEntity,Integer> {
}
