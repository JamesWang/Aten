package com.aten.cnz.stack.info;

import java.util.logging.Logger;

import org.junit.Test;

public class StackUtilsTest {

    @Test
    public void printLog(){
	Logger log = Logger.getLogger( StackUtils.class.getName());
	StackUtils.debug(log, "Very Good!");
	System.out.println(StackUtils.stackInfo("Nice"));
	System.out.println(StackUtils.formatWithStackInfo("Perfect"));
    }
}
