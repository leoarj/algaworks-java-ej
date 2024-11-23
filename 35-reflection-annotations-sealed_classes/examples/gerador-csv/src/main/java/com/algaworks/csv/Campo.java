package com.algaworks.csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para ser processada na geração do CSV.
 * Campos anotados com ela serão lidos por Reflection,
 * com uso de Field.isAnnotationPresent.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Campo {
}
