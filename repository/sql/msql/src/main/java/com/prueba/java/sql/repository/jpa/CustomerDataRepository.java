package com.prueba.java.sql.repository.jpa;

import com.prueba.java.sql.repository.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDataRepository extends CrudRepository<CustomerEntity,Integer> {
}
