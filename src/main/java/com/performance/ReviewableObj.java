package com.performance;


/**
 * @author Hui
 *
 * 考核对象的抽象，员工、部门、单位这三类考核对象实现此接口
 */
public interface ReviewableObj {
	String getCode();
	String getName();
	String getType();
	void setType(String type);
}
