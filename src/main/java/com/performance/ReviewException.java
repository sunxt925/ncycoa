package com.performance;

@SuppressWarnings("serial")
public class ReviewException extends Exception {
	public ReviewException(){
		
	}
	
	public ReviewException(String msg){
		super(msg);
	}
	
	private int code = 0;
	public ReviewException(String msg, int code){
		super(msg);
		this.code = code;
	}
	
	public int getReviewExceptionCode(){
		return code;
	}
	
	public ReviewException(String msg, Throwable e){
		super(msg, e);
	}
	
	
	public final static int PARA_VALUE_NOT_EXIST = 1;
}
