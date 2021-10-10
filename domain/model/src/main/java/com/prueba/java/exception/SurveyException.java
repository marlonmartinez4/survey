package com.prueba.java.exception;


import com.prueba.java.enums.SurveyEnum;

public class SurveyException extends RuntimeException{
    public SurveyException(SurveyEnum surveyEnum) {
        super(surveyEnum.name());
    }
}
