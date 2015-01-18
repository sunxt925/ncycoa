package com.performance;

@SuppressWarnings("serial")
public class DateParseException extends Exception {
	public DateParseException(){
		
	}
	
	public DateParseException(String msg){
		super(msg);
	}
	
	public DateParseException(String msg, Throwable e){
		super(msg, e);
	}
}
