package com.review;

@SuppressWarnings("serial")
public class ReviewException extends Exception {
	public ReviewException(){
		super();
	}
	
	public ReviewException(String msg){
		super(msg);
	}
	
	public ReviewException(String msg, Throwable e){
		super(msg, e);
	}
}
