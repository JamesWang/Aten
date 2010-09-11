/** Copyright (c) 2010, Jianjun Wang <jooly.wang@gmail.com>
 *  All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 */
package com.aten.cnz.stack.info;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Jianjun Wang
 * 
 */
public class StackUtils {
    /**
     * This method is called by the debug method, so the index of
     * getStackTrace()[] is 2
     * 
     * @param message
     * @return
     */
    private static String format(String message) {

	/**
	 * ex.getStackTrace()[0] is this format() method
	 * ex.getStackTrace()[1] is the debug method ex.getStackTrace()[2] is
	 * the method you call debug()
	 */
	StackTraceElement ste = new Throwable().getStackTrace()[2];

	String callerClassName = ste.getClassName();
	int callerLineNumber = ste.getLineNumber();
	callerClassName = callerClassName.substring(callerClassName
		.lastIndexOf(".") + 1);

	return "[" + callerClassName + ":" + callerLineNumber + "]" + message;
    }

    /**
     * This method should be called directly by the method you want to put log
     * there
     * 
     * @param message
     * @return
     */
    public static String formatWithStackInfo(String message) {

	/**
	 * ex.getStackTrace()[0] is this format() method ex.getStackTrace()[1]
	 * is your method where you call this method
	 */
	StackTraceElement ste = new Throwable().getStackTrace()[1];

	String callerClassName = ste.getClassName();
	int callerLineNumber   = ste.getLineNumber();
	String methodName      = ste.getMethodName();
	callerClassName = callerClassName.substring(callerClassName
		.lastIndexOf(".") + 1);

	return "[" + callerClassName +"." + methodName + ":" + callerLineNumber + "]" + message;
    }

    /**
     * You pass in a logger, here it is java.util.logging.Logger, you may change
     * it to log4j if you like
     * 
     * @param log
     * @param message
     */
    public static void debug(Logger log, String message) {
	log.log(Level.FINE, format(message));
    }
    /**
     * You pass in a logger, here it is java.util.logging.Logger, you may change
     * it to log4j if you like
     * 
     * @param log
     * @param message
     */
    public static String stackInfo( String message) {
	return format(message);
    }
}
