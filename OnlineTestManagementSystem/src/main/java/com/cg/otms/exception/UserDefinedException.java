package com.cg.otms.exception;

public class UserDefinedException extends RuntimeException {
	public UserDefinedException(String msg)
	{
		super(msg);
	}
	public UserDefinedException(String msg,Throwable e)
	{
		super(msg,e);
	}
}