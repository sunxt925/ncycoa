package edu.cqu.gem.ncycoa.util;


public class PagerUtil {
	
	/**
	 * ��ȡ��ҳ������¼�����м�¼�е�index
	 * 
	 * @param rowCounts
	 *            ��¼����
	 * @param pageNO
	 *            ҳ��
	 * @param pageSize
	 *            ÿҳ��¼��
	 */
	public static int getOffset(int rowCounts, int pageNO, int pageSize) {
		return (validatePageNO(rowCounts, pageNO, pageSize) - 1) * pageSize;
	}

	/**
	 * ����ҳ�ţ�ȷ��������1~~PageSum֮��
	 * 
	 * @param rowCounts
	 *            ��¼����
	 * @param pageNO
	 *            ҳ��
	 * @param pageSize
	 *            ÿҳ��¼��
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