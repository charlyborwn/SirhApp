package com.hrm.sirhapp;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.inputmask.InputMask;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@FacesValidator("percentageMaskValidator")
public class PercentageMaskValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String submittedValue = context.getExternalContext().getRequestParameterMap().get(component.getClientId(context));

        if (submittedValue == null || submittedValue.isEmpty()) {
            return; // Let @NotNull / required="true" handle.
        }

        InputMask input = (InputMask) component;
        String mask = input.getMask();
        StringBuilder regex = new StringBuilder();

        regex.append("\\b(?<!\\.)(?:\\d|[1-9]\\d|100)%");

        if (!submittedValue.matches(regex.toString()) || submittedValue.equals("0%")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Porcentaje", submittedValue + " no es un porcentaje valido, Ej. Entre 1% y 100%"));
        }
    }
}
