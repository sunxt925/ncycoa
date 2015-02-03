package edu.cqu.gem.ncycoa.util;


public class PagerUtil {
	
	/**
	 * 获取本页首条记录在所有记录中的index
	 * 
	 * @param rowCounts
	 *            记录总数
	 * @param pageNO
	 *            页号
	 * @param pageSize
	 *            每页记录数
	 */
	public static int getOffset(int rowCounts, int pageNO, int pageSize) {
		return (validatePageNO(rowCounts, pageNO, pageSize) - 1) * pageSize;
	}

	/**
	 * 修正页号，确保其落在1~~PageSum之间
	 * 
	 * @param rowCounts
	 *            记录总数
	 * @param pageNO
	 *            页号
	 * @param pageSize
	 *            每页记录数
	 */
	public static int validatePageNO(int rowCounts, int pageNO, int pageSize) {
		final int pages = (int) Math.ceil((double) rowCounts / pageSize);
		if (pageNO > pages)
			pageNO = pages;
		if (pageNO <= 1)
			pageNO = 1;

		return pageNO;
	}
	
	private PagerUtil() { }
	
}