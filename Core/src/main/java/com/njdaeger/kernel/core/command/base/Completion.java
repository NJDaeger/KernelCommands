package com.njdaeger.kernel.core.command.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Declares a method as a completion method
 */
@Target( ElementType.METHOD)
public @interface Completion {
	
	/**
	 * The list of commands this completion is for
	 */
	String[] commands() default "";
	
}
