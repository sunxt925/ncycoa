package com.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * DateTime Util.
 * @version 0.3.5
 * @author Xiaobo Liu
 */
public class DateTimeUtil {
	/**
	 * Return current datetime string.
	 * @return current datetime, pattern: "yyyy-MM-dd HH:mm:ss".
	 */
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		return sdf.format(new Date());
	}
	
	/**
	 * 公共方法，传入Oralce生成的日期型，返回标准的yyyy-mm-dd格式
	 * @param dd
	 * @return
	 */
	public static String formatDateOracle(Date dd){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dd);
	}
	
	
	/**
	 * Return current datetime string.
	 * @return current datetime, pattern: "yyyy-MM-dd".
	 */
	public static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		return sdf.format(new Date());
	}

	/**
	 * Return current datetime string.
	 * @return current datetime, pattern: "yyyy-MM-dd".
	 */
	public static String getDateFmt(String fmt){
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);		
		return sdf.format(new Date());
	}
	
	
	/**
	 * Return current datetime string.
	 * @return current datetime, pattern: "yyyy-MM-dd".
	 */
	public static String getYyyy(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");		
		return sdf.format(new Date());
	}
	public static String toOracleDate(java.sql.Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-m-d");
		String dt = sdf.format(d);
		return dt;
	}

	/**
	 * Return current datetime string.
	 * @param pattern format pattern
	 * @return current datetime
	 */
	public static String getDateTime(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dt = sdf.format(new Date());
		return dt;
	}

	public static String getDateTime(String pattern, Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dt = sdf.format(d);
		return dt;
	}

	/**
	 * Return short format datetime string.
	 * @param date java.util.Date
	 * @return short format datetime
	 */
	public static String shortFmt(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return sdf.format(date);
	}
	

	/**
	 * Parse a datetime string.
	 * @param param datetime string, pattern: "yyyy-MM-dd HH:mm:ss".
	 * @return java.util.Date
	 */
	public static Date parse(String param) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(param);
		} catch (ParseException ex) {
		}
		return date;
	}
	/*
	  //输入：表单名称前缀，年，月，日
	  //输出：从1950-2010的下拉日期菜单，菜单名称为分别为前缀_y,前缀_m,前缀_d
	  public static String dateList(String label, String y, String m, String d,
	                                int fromYear, int toYear) {
	    String str = "<SELECT name='" + label + "_y'>";
	    for (int i = fromYear; i < toYear; i++) {
	      if (y == null || y.length() == 0) {
	        java.sql.Date _d = new java.sql.Date(System.currentTimeMillis());
	        y = String.valueOf(_d.getYear() + 1900);
	      }
	      if (Integer.parseInt(y) == i) {
	        str = str + "<option value=" + i + " selected>" + i + "</option>";
	      }
	      else {
	        str = str + "<option value=" + i + ">" + i + "</option>";
	
	      }
	    }
	    str = str + "</SELECT >年<SELECT name ='" + label +
	        "_m' >";
	    for (int i = 1; i < 13; i++) {
	      if (m == null || m.length() == 0) {
	        java.sql.Date _d = new java.sql.Date(System.currentTimeMillis());
	        m = String.valueOf(_d.getMonth() + 1);
	      }
	      if (Integer.parseInt(m) == i) {
	        str = str + "<option value=" + i + " selected>" + i + "</option>";
	      }
	      else {
	        str = str + "<option value=" + i + ">" + i + "</option>";
	      }
	    }
	    str = str + "</SELECT >月<SELECT name ='" + label +
	        "_d'  >";
	    for (int i = 1; i < 32; i++) {
	      if (d == null || d.length() == 0) {
	        java.sql.Date _d = new java.sql.Date(System.currentTimeMillis());
	        d = String.valueOf(_d.getDate());
	      }
	      if (Integer.parseInt(d) == i) {
	        str = str + "<option value=" + i + " selected>" + i + "</option>";
	      }
	      else {
	        str = str + "<option value=" + i + ">" + i + "</option>";
	      }
	    }
	    str = str + "</SELECT>日";
	    return str;
	  }
	
	  //输入：表单名称前缀，年，月
	  //输出：从1950-2010的下拉日期菜单，菜单名称为分别为前缀_y,前缀_m
	  public static String dateList(String label, String y, String m, int fromYear,
	                                int toYear) {
	    String str = "<SELECT name='" + label + "_y' >";
	    for (int i = fromYear; i < toYear; i++) {
	      if (y == null || y.length() == 0) {
	        java.sql.Date _d = new java.sql.Date(System.currentTimeMillis());
	        y = String.valueOf(_d.getYear() + 1900);
	      }
	      if (Integer.parseInt(y) == i) {
	        str = str + "<option value=" + i + " selected>" + i + "</option>";
	      }
	      else {
	        str = str + "<option value=" + i + ">" + i + "</option>";
	
	      }
	    }
	    str = str + "</SELECT >年<SELECT name ='" + label +
	        "_m' >";
	    for (int i = 1; i < 13; i++) {
	      if (m == null || m.length() == 0) {
	        java.sql.Date _d = new java.sql.Date(System.currentTimeMillis());
	        m = String.valueOf(_d.getMonth() + 1);
	      }
	      if (Integer.parseInt(m) == i) {
	        str = str + "<option value=" + i + " selected>" + i + "</option>";
	      }
	      else {
	        str = str + "<option value=" + i + ">" + i + "</option>";
	      }
	    }
	    str = str + "</SELECT >月";
	    return str;
	  }
	
	  //输入：年月日
	//输出：sql的日期型
	  public static java.sql.Date getSqlDate(String year, String month, String date) {
	    int y = 100;
	    int m = 2;
	    int d = 1;
	    if (year != null) {
	      y = Integer.parseInt(year) - 1900;
	    }
	    if (month != null) {
	      m = Integer.parseInt(month) - 1;
	    }
	    if (date != null) {
	      d = Integer.parseInt(date);
	    }
	    java.sql.Date SqlDate = new java.sql.Date(y, m, d);
	    return SqlDate;
	  }
	
	  //输入：表单名称前缀，年，月，日，需要默认显示的日期
	//输出：从1950-2010的下拉日期菜单，菜单名称为分别为前缀_y,前缀_m,前缀_d
	/*
	  public static String dateList(String label, String y, String m, String d,
	                                int fromYear, int toYear, String showDate) {
	
	    String defaultval = "1950-1-1";
	    String[] show = defaultval.split("-");
	    if (showDate != null) {
	      show = showDate.split("-");
	    }
	    String str = "<SELECT name='" + label + "_y'>";
	    for (int i = fromYear; i < toYear; i++) {
	
	      if (Integer.parseInt(show[0]) == i) {
	        str = str + "<option value=" + i + " selected>" + i + "</option>";
	      }
	      else {
	        str = str + "<option value=" + i + ">" + i + "</option>";
	
	      }
	    }
	    str = str + "</SELECT >年<SELECT name ='" + label +
	        "_m'  >";
	    for (int i = 1; i < 13; i++) {
	
	      if (Integer.parseInt(show[1]) == i) {
	        str = str + "<option value=" + i + " selected>" + i + "</option>";
	      }
	      else {
	        str = str + "<option value=" + i + ">" + i + "</option>";
	      }
	    }
	    str = str + "</SELECT >月<SELECT name ='" + label +
	        "_d'  >";
	    for (int i = 1; i < 32; i++) {
	      if (Integer.parseInt(show[2]) == i) {
	        str = str + "<option value=" + i + " selected>" + i + "</option>";
	      }
	      else {
	        str = str + "<option value=" + i + ">" + i + "</option>";
	      }
	    }
	    str = str + "</SELECT>日";
	    return str;
	  }
	
	  public static String dateList(String label, String y, String m, String d,
	                                int fromYear, int toYear,
	                                java.sql.Date showDate) {
	
	    String defaultval = "1950-1-1";
	    String[] show = defaultval.split("-");
	    if (showDate != null) {
	      show = showDate.toString().split("-");
	    }
	    String str = "<SELECT name='" + label + "_y'>";
	    for (int i = fromYear; i < toYear; i++) {
	
	      if (Integer.parseInt(show[0]) == i) {
	        str = str + "<option value=" + i + " selected>" + i + "</option>";
	      }
	      else {
	        str = str + "<option value=" + i + ">" + i + "</option>";
	
	      }
	    }
	    str = str + "</SELECT >年<SELECT name ='" + label +
	        "_m' >";
	    for (int i = 1; i < 13; i++) {
	
	      if (Integer.parseInt(show[1]) == i) {
	        str = str + "<option value=" + i + " selected>" + i + "</option>";
	      }
	      else {
	        str = str + "<option value=" + i + ">" + i + "</option>";
	      }
	    }
	    str = str + "</SELECT >月<SELECT name ='" + label +
	        "_d' >";
	    for (int i = 1; i < 32; i++) {
	      if (Integer.parseInt(show[2]) == i) {
	        str = str + "<option value=" + i + " selected>" + i + "</option>";
	      }
	      else {
	        str = str + "<option value=" + i + ">" + i + "</option>";
	      }
	    }
	    str = str + "</SELECT>日";
	    return str;
	  }
	*/
	public static void main(String[] args) {
		//System.out.println(dateList("born", null, null, null, 1960, 2002));
		Date d = new Date();
		System.out.println(
			DateTimeUtil.getDateTime("yyyy 年 M 月 d日:HH 时 mm 分 ss 秒 ", d));

	}

	/**
	 * 解析象yyyy-mm-dd类型的字符为一个date
	 * @param d
	 * @return
	 */
	public static Date parseYmd(String d) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(d);
		} catch (ParseException ex) {
		}
		return date;

	}

	/**
	 * 由java.util.Date到java.sql.Date的类型转换
	 * @param date
	 * @return Date
	 */	
	public static java.sql.Date getSqlDate(java.util.Date date){
		java.sql.Date dt=new java.sql.Date(date.getTime());
		return dt;
	}

	public static Date nowDate(){
		Calendar calendar=Calendar.getInstance();
		return getSqlDate(calendar.getTime());
	}
	
	/**
	 * 获得某一日期的后一天
	 * @param date
	 * @return Date
	 */
	public static Date getNextDate(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int day=calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE,day+1);
		return getSqlDate(calendar.getTime());
	}
	
	/**
	 * 获得某一日期的前一天
	 * @param date
	 * @return Date
	 */
	public static Date getPreviousDate(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int day=calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE,day-1);
		return getSqlDate(calendar.getTime());
	}
	
	/**
	 * 获得某年某月第一天的日期
	 * @param year
	 * @param month
	 * @return Date
	 */
	public static Date getFirstDayOfMonth(int year,int month){
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month-1);
		calendar.set(Calendar.DATE,1);
		return getSqlDate(calendar.getTime());
	}
	
	/**
	 * 获得某年某月最后一天的日期
	 * @param year
	 * @param month
	 * @return Date
	 */
	public static Date getLastDayOfMonth(int year,int month){
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month);
		calendar.set(Calendar.DATE,1);
		return getPreviousDate(getSqlDate(calendar.getTime()));
	}

	/**
	 * 获得当年当月最后一天的日期
	 * @return Date
	 */
	public static Date getLastDayOfCurMonth(){
		int year=Integer.parseInt(getDate().substring(0,4));
		int month=Integer.parseInt(getDate().substring(5,7));
		return getLastDayOfMonth(year,month);
	}
	
	/**
	 * 获得当月的限缴日期
	 * @return Date
	 */
	public static Date getXjrq()
	{
		int day=Integer.parseInt(getDate().substring(8,10));
		if(day<=10)
		{
			int year=Integer.parseInt(getDate().substring(0,4));
			int month=Integer.parseInt(getDate().substring(5,7));
			return buildDate(year,month,10);
		}
		else
			return getLastDayOfCurMonth();
	}
	
	/**
	 * 由年月日构建java.sql.Date类型
	 * @param year
	 * @param month
	 * @param date
	 * @return Date
	 */
	public static Date buildDate(int year,int month,int date){
		Calendar calendar=Calendar.getInstance();
		calendar.set(year,month-1,date);
		return getSqlDate(calendar.getTime());
	}
	
	/**
	 * 取得某月的天数
	 * @param year
	 * @param month
	 * @return int
	 */
	public static int getDayCountOfMonth(int year,int month){
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month);
		calendar.set(Calendar.DATE,0);
		return calendar.get(Calendar.DATE);
	}
	
	/**
	 * 获得某年某季度的最后一天的日期
	 * @param year
	 * @param quarter
	 * @return Date
	 */
	public static Date getLastDayOfQuarter(int year,int quarter){
		int month=0;
		if(quarter>4){
			return null;
		}else{
			month=quarter*3;
		}
		return getLastDayOfMonth(year,month);
		
	}
	
	/**
	 * 获得某年某季度的第一天的日期
	 * @param year
	 * @param quarter
	 * @return Date
	 */
	public static Date getFirstDayOfQuarter(int year,int quarter){
		int month=0;
		if(quarter>4){
			return null;
		}else{
			month=(quarter-1)*3+1;
		}
		return getFirstDayOfMonth(year,month);
	}
	
	/**
	 * 获得某年的第一天的日期
	 * @param year
	 * @return Date
	 */
	public static Date getFirstDayOfYear(int year){
		return getFirstDayOfMonth(year,1);
	}
	
	/**
	 * 获得某年的最后一天的日期
	 * @param year
	 * @return Date
	 */
	public static Date getLastDayOfYear(int year){
		return getLastDayOfMonth(year,12);
	}
	
	/**
	 * String到java.sql.Date的类型转换
	 * @param param
	 * @return Date
	 */
	
	public static Date roundDate(Date dt){
		String sdate=getDate();
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(sdate);
		} catch (ParseException ex) {
		}
		return date;		
	}
}
