package com.hrm.sirhapp;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.Validator;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@FacesValidator("selectOneMenuValidatorInt")
public class SelectOneMenuValidatorInt implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Integer submittedValue = (Integer) value;
        String clientId = component.getClientId(context);
        String label = (String) component.getAttributes().get("label");
        if (label == null || label.trim().isEmpty()) {
            label = clientId;
        }

        if (submittedValue ==-1) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary(label + ": Seleccione una opción");
            message.setDetail(label + ": Seleccione una opción");
            System.out.println("EL VALOR ES NULO POR LO TANTO HAY UNA EXCEPCION" + submittedValue);
            throw new ValidatorException(message);
        }
    }

}
