package com.common;

import com.db.*;

public class Log
{
	public static void Debug(String instr)
	{
		if (ConnectionPool.Debug.equals("on"))
		{
			System.out.println(instr);
		}
	}

	public static void Debug(int a)
	{
		Debug(String.valueOf(a));
	}

	public static void Trace(String instr)
	{
		Debug(instr);
	}

	public static void Trace(int a)
	{
		Trace(String.valueOf(a));
	}

	public static void Info(String instr)
	{
		Debug(instr);
	}

	public static void Info(int a)
	{
		Info(String.valueOf(a));
	}
}
