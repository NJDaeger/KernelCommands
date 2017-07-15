package com.njdaeger.kernel.core.command.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Declares a method as a command
 */
@Target(ElementType.METHOD)
public @interface Command {
	
	/**
	 * The maximum amount of arguments allowed for this command
	 */
	int max() default -1;
	
	/**
	 * The minimum amount of arguments allowed for this command
	 */
	int min() default -1;
	
	/**
	 * The name of this command
	 */
	String name();
	
	/**
	 * The command aliases
	 */
	String[] aliases() default {};
	
	/**
	 * The command description
	 */
	String desc() default "";
	
	/**
	 * The usage of the command
	 */
	String usage() default "";
	
	/**
	 * The permissions needed to use this command
	 */
	String[] permissions() default {};
	
	/**
	 * Checks if the command needs to be executed by an operator
	 */
	boolean needsOp() default false;

}
