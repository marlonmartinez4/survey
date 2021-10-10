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
@Table(name = "respuesta_de_encuesta")
public class AnswerSurveyEntity {

    @Id
    private int id;

    @Column(name = "nombre")
    private String name;
}
