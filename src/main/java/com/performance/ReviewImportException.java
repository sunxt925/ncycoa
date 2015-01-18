package com.performance;

@SuppressWarnings("serial")
public class ReviewImportException extends Exception {
	public ReviewImportException(){
		
	}
	
	public ReviewImportException(String msg){
		super(msg);
	}
	
	public ReviewImportException(String msg, Throwable e){
		super(msg, e);
	}
}
