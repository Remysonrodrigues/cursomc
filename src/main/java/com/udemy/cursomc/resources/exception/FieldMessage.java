package com.udemy.cursomc.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    private String fieldName;
    private String messagem;

    public FieldMessage() {}

    public FieldMessage(String fieldName, String messagem) {
        this.fieldName = fieldName;
        this.messagem = messagem;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return messagem;
    }

    public void setMessage(String messagem) {
        this.messagem = messagem;
    }
}
