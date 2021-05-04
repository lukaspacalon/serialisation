package com.inetpsa.serialisation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Alias {
	String fr() default "default";
	String en() default "default";
}
