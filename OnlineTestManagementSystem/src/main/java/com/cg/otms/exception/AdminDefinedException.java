
package com.cg.otms.exception;

public class AdminDefinedException extends RuntimeException {
	public AdminDefinedException(String msg)
	{
		super(msg);
	}
	public AdminDefinedException(String msg,Throwable e)
	{
		super(msg,e);
	}
}