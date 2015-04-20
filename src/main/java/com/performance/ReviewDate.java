package com.performance;


/**
 * @author Hui
 * 考核时间，目前period只支持月份
 */
public class ReviewDate {
	private int year;
	private char periodtype;
	private int period;
	
	public ReviewDate(String year, String periodcode) throws DateParseException {
		try{
			this.year = Integer.parseInt(year);
			this.periodtype = periodcode.charAt(0);
			this.period = Integer.parseInt(periodcode.substring(1));
		} catch (IndexOutOfBoundsException e){
			throw new DateParseException();
		} catch (NumberFormatException e){
			throw new DateParseException();
		}
	}
	
	public ReviewDate(String year) throws DateParseException {
		try{
			this.year = Integer.parseInt(year);
		} catch (NumberFormatException e){
			throw new DateParseException();
		}
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public char getPeriodtype() {
		return periodtype;
	}
	
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	
	public String getPeriodCode(){
		return periodtype + String.format("%02d", period);
	}
	
	public void setPeriodCode(String periodcode) throws DateParseException {
		try{
			this.periodtype = periodcode.charAt(0);
			this.period = Integer.parseInt(periodcode.substring(1));
		} catch (IndexOutOfBoundsException e){
			throw new DateParseException();
		} catch (NumberFormatException e){
			throw new DateParseException();
		}
	}
	
	public int getMin(){
		switch(periodtype){
		case 'M':return period;
		case 'S':
			return (period - 1) * 3 + 1;
		case 'H':
			return (period - 1) * 6 + 1;
		case 'Y':
			return 1;
		}
		return 0;
	}
	
	public int getMax(){
		switch(periodtype){
		case 'M':return period;
		case 'S':
			return period * 3;
		case 'H':
			return period * 6;
		case 'Y':
			return 12;
		}
		return 0;
	}
	
	//>>>>>>??????????????????????当前仅支持月份类型的ReviewDate做此操作
	public String getPeriodCode(String type){
		if("D00.H00".equals(type)){
			return "H0" + ((period - 1) / 6 + 1);
		}else if("D00.Y00".equals(type)){
			return "Y01";
		}else if("D00.S00".equals(type)){
			return "S0" + ((period - 1) / 3 + 1);
		}
		return getPeriodCode();
	}
}
