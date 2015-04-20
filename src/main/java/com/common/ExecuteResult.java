package com.common;

public class ExecuteResult
{
	public boolean res = false;
	public String res_str = "";

	public ExecuteResult()
	{

	}

	public boolean isRes()
	{
		return res;
	}

	public void setRes(boolean res)
	{
		this.res = res;
	}

	public String getRes_str()
	{
		return res_str;
	}

	public void setRes_str(String resStr)
	{
		res_str = resStr;
	}
}