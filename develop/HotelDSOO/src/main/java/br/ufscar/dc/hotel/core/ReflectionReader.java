/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface ReflectionReader {

    default public List<Field> getInheritedFields() {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = this.getClass(); c != null; c = c.getSuperclass()) {
            List<Field> list = Arrays.asList(c.getDeclaredFields());
            Collections.reverse(list);
            fields.addAll(list);
        }
        Collections.reverse(fields);
        return fields;
    }

    default public List<Field> getDeclaredFields() {
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(this.getClass().getDeclaredFields()));
        return fields;
    }

    default public Field getField(String attribute) {
        for (Field field : getInheritedFields()) {
            if (field.getName().equals(attribute)) {
                return field;
            }
        }
        return null;
    }

}
