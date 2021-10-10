package com.prueba.java.sql.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "modulo_de_preguntas")
public class QuestionModuleEntity {

    @Id
    private int id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "estado")
    private String status;
}
