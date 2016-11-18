/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.core;

import java.lang.reflect.Field;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface ReflectionValidation extends ReflectionReader {

    default public boolean getRequired(String attribute) {
        return getField(attribute).isAnnotationPresent(NotNull.class);
    }

    default public String getRequiredMessage(String attribute) {
        if (getRequired(attribute)) {
            NotNull required = (NotNull) getField(attribute).getAnnotation(NotNull.class);
            return required.message();
        }
        return "";
    }

    default public int getSize(String attribute) {
        Field field = getField(attribute);
        if (field.isAnnotationPresent(Size.class)) {
            Size size = (Size) field.getAnnotation(Size.class);
            return size.max();
        }
        return 256;
    }

}
