package com.njdaeger.kernel.core.command.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface Completion {
	
	/**
	 * All the commands this completion is for
	 */
	String[] commands();
	
}
