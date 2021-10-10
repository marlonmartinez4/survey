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
@Table(name = "preguntas_de_encuesta")
public class QuestionSurveyEntity {

    @Id
    private int id;

    @Column(name = "modulo_preguntas_id")
    private int questionModuleId;

    @Column(name = "descripcion")
    private String description;

}
