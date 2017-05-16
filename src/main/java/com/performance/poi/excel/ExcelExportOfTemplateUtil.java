package com.performance.poi.excel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import com.performance.poi.excel.annotation.Excel;
import com.performance.poi.excel.annotation.ExcelCollection;
import com.performance.poi.excel.annotation.ExcelTarget;
import com.performance.poi.excel.entity.ComparatorExcelField;
import com.performance.poi.excel.entity.ExportField;
import com.performance.poi.excel.entity.TemplateExportParams;

/**
 * Excel ��������ģ�嵼��
 */
public final class ExcelExportOfTemplateUtil {

	/**
	 * �����ļ�ͨ��ģ�����
	 * 
	 * @param entity
	 *            ��������
	 * @param pojoClass
	 *            ��Ӧʵ��
	 * @param dataSet
	 *            ʵ�弯��
	 * @param map
	 *            ģ�弯��
	 * @return
	 */
	public static Workbook exportExcel(TemplateExportParams params, Class<?> pojoClass, Collection<?> dataSet, Map<String, Object> map) {
		return createSheetInUserModel2FileByTemplate(params, pojoClass, dataSet, map);
	}

	/**
	 * �����ļ�ͨ��ģ�����ֻ��ģ��,û�м���
	 * 
	 * @param entity
	 *            ��������
	 * @param pojoClass
	 *            ��Ӧʵ��
	 * @param map
	 *            ģ�弯��
	 * @return
	 */
	public static Workbook exportExcel(TemplateExportParams params, Map<String, Object> map) {
		return createSheetInUserModel2FileByTemplate(params, null, null, map);
	}

	private static Workbook createSheetInUserModel2FileByTemplate(TemplateExportParams params, Class<?> pojoClass, Collection<?> dataSet,
			Map<String, Object> map) {
		// step 1. �ж�ģ��ĵ�ַ
		if (StringUtils.isEmpty(params.getTemplateUrl())) {
			return null;
		}
		Workbook wb = null;
		try {
			// step 2. ����ģ�崴��Workbook
			wb = getCloneWorkBook(params.getTemplateUrl());
			// step 3. ɾ��������sheet
			for (int i = wb.getNumberOfSheets() - 1; i >= 0; i--) {
				if (i != params.getSheetNum()) {
					wb.removeSheetAt(i);
				}
			}
			if (StringUtils.isNotEmpty(params.getSheetName())) {
				wb.setSheetName(0, params.getSheetName());
			}
			// step 4. ����ģ��
			parseTemplate(wb.getSheetAt(0), map);
			if (dataSet != null) {
				// step 5. �������������
				addDataToSheet(params, pojoClass, dataSet, wb.getSheetAt(0), wb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return wb;
	}

	/**
	 * ��¡excel�ļ���ֹ����ԭ�ļ�, workbook�޷���¡ֻ�ܶ�excel�ļ����п�¡
	 */
	private static Workbook getCloneWorkBook(final String rpath) throws Exception {
		Workbook wb = null;
		String path = ExcelPublicUtil.getAbsolutePath(rpath);
		FileInputStream fileis = new FileInputStream(path);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = fileis.read(buffer)) > -1) {
			baos.write(buffer, 0, len);
		}
		baos.flush();
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		wb = WorkbookFactory.create(is);
		// �ر��õ�����
		baos.close();
		fileis.close();
		is.close();
		return wb;
	}

	/**
	 * Sheet �����������
	 * 
	 * @param params
	 * @param pojoClass
	 * @param dataSet
	 * @param workbook
	 */
	private static void addDataToSheet(TemplateExportParams params, Class<?> pojoClass, Collection<?> dataSet, Sheet sheet, Workbook workbook) throws Exception {
		Drawing patriarch = sheet.createDrawingPatriarch();
		List<ExportField> excelParams = new ArrayList<ExportField>();
		// �õ�????�ֶ�
		Field fileds[] = ExcelPublicUtil.getClassFields(pojoClass);
		ExcelTarget etarget = pojoClass.getAnnotation(ExcelTarget.class);
		String targetId = null;
		if (etarget != null) {
			targetId = etarget.id();
		}
		getAllExcelField(targetId, fileds, excelParams, pojoClass, null);
		sortAllParams(excelParams);
		Iterator<?> its = dataSet.iterator();
		int index = sheet.getLastRowNum();
		while (its.hasNext()) {
			Object t = its.next();
			index += createCells(patriarch, index, t, excelParams, sheet, workbook);
		}
	}

	private static void parseTemplate(Sheet sheet, Map<String, Object> map) throws Exception {
		Iterator<Row> rows = sheet.rowIterator();
		Row row;
		while (rows.hasNext()) {
			row = rows.next();
			for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
				setValueForCellByMap(row.getCell(i), map);
			}
		}
	}

	/**
	 * ��ÿ��Cellͨ��������ʽset??
	 * 
	 * @param cell
	 * @param map
	 */
	private static void setValueForCellByMap(Cell cell, Map<String, Object> map) throws Exception {
		String oldString;
		try {
			// step 1. �ж����cell�����ǲ��Ǻ�??
			oldString = cell.getStringCellValue();
		} catch (Exception e) {
			return;
		}
		if (oldString != null && oldString.indexOf("{{") != -1) {
			// setp 2. �ж��Ƿ��н�������
			String params;
			while (oldString.indexOf("{{") != -1) {
				params = oldString.substring(oldString.indexOf("{{") + 2, oldString.indexOf("}}"));
				oldString = oldString.replace("{{" + params + "}}", getParamsValue(params.trim(), map));
			}
			cell.setCellValue(oldString);
		}
	}

	/**
	 * ��ȡ����??
	 * 
	 * @param params
	 * @param map
	 * @return
	 */
	private static String getParamsValue(String params, Map<String, Object> map) throws Exception {
		if (params.indexOf(".") != -1) {
			String[] paramsArr = params.split("\\.");
			return getValueDoWhile(map.get(paramsArr[0]), paramsArr, 1);
		}
		return map.containsKey(params) ? map.get(params).toString() : "";
	}

	/**
	 * ͨ��������ȥ����??
	 * 
	 * @param object
	 * @param paramsArr
	 * @param index
	 * @return
	 * @throws Exception
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("rawtypes")
	private static String getValueDoWhile(Object object, String[] paramsArr, int index) throws Exception {
		if (object == null) {
			return "";
		}
		if (object instanceof Map) {
			object = ((Map) object).get(paramsArr[index]);
		} else {
			object = ExcelPublicUtil.getMethod(paramsArr[index], object.getClass(), false).invoke(object, new Object[] {});
		}
		return (index == paramsArr.length - 1) ? (object == null ? "" : object.toString()) : getValueDoWhile(object, paramsArr, ++index);
	}

	/**
	 * ���ֶθ����û�������??
	 */
	private static void sortAllParams(List<ExportField> excelParams) {
		Collections.sort(excelParams, new ComparatorExcelField());
		for (ExportField entity : excelParams) {
			if (entity.getList() != null) {
				Collections.sort(entity.getList(), new ComparatorExcelField());
			}
		}
	}

	/**
	 * ���� ????Ҫ�� Cells
	 * 
	 * @param styles
	 * @throws Exception
	 */
	private static int createCells(Drawing patriarch, int index, Object t, List<ExportField> excelParams, Sheet sheet, Workbook workbook)
			throws Exception {
		ExportField entity;
		Row row = sheet.createRow(index);
		row.setHeight((short) 350);
		int maxHeight = 1, cellNum = 0;
		for (int k = 0, paramSize = excelParams.size(); k < paramSize; k++) {
			entity = excelParams.get(k);
			if (entity.getList() != null) {
				Collection<?> list = (Collection<?>) entity.getGetMethod().invoke(t, new Object[] {});
				int listC = 0;
				for (Object obj : list) {
					createListCells(patriarch, index + listC, cellNum, obj, entity.getList(), sheet, workbook);
					listC++;
				}
				cellNum += entity.getList().size();
				if (list != null && list.size() > maxHeight) {
					maxHeight = list.size();
				}
			} else {
				Object value = getCellValue(entity, t);
				if (entity.getType() != 2) {
					createStringCell(row, cellNum++, value.toString(), entity, workbook);
				} else {
					createImageCell(patriarch, entity, row, cellNum++, value == null ? "" : value.toString(), t, workbook);
				}
			}
		}
		// �ϲ�????�ϲ��ĵ�Ԫ��
		cellNum = 0;
		for (int k = 0, paramSize = excelParams.size(); k < paramSize; k++) {
			entity = excelParams.get(k);
			if (entity.getList() != null) {
				cellNum += entity.getList().size();
			} else if (entity.isNeedMerge()) {
				sheet.addMergedRegion(new CellRangeAddress(index, index + maxHeight - 1, cellNum, cellNum));
				cellNum++;
			}
		}
		return maxHeight;

	}

	/**
	 * ��ȡ�������cell��??,�ṩ????���ӹ���
	 * 
	 * @param entity
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private static Object getCellValue(ExportField entity, Object obj) throws Exception {
		Object value = entity.getGetMethods() != null ? getFieldBySomeMethod(entity.getGetMethods(), obj) : entity.getGetMethod().invoke(obj, new Object[] {});
		// step 1 �ж��ǲ�����??????????��ʽ??
		if (StringUtils.isNotEmpty(entity.getExportFormat()) && StringUtils.isNotEmpty(entity.getDatabaseFormat())) {
			Date temp = null;
			if (value instanceof String) {
				SimpleDateFormat format = new SimpleDateFormat(entity.getDatabaseFormat());
				temp = format.parse(value.toString());
			} else if (value instanceof Date) {
				temp = (Date) value;
			}
			if (temp != null) {
				SimpleDateFormat format = new SimpleDateFormat(entity.getExportFormat());
				value = format.format(temp);
			}
		}
		return value == null ? "" : value.toString();
	}

	/**
	 * ����List֮��ĸ���Cells
	 * 
	 * @param styles
	 */
	private static void createListCells(Drawing patriarch, int index, int cellNum, Object obj, List<ExportField> excelParams, Sheet sheet,
			Workbook workbook) throws Exception {
		ExportField entity;
		Row row;
		if (sheet.getRow(index) == null) {
			row = sheet.createRow(index);
			row.setHeight((short) 350);
		} else {
			row = sheet.getRow(index);
		}
		for (int k = 0, paramSize = excelParams.size(); k < paramSize; k++) {
			entity = excelParams.get(k);
			Object value = getCellValue(entity, obj);
			if (entity.getType() != 2) {
				createStringCell(row, cellNum++, value == null ? "" : value.toString(), entity, workbook);
			} else {
				createImageCell(patriarch, entity, row, cellNum++, value == null ? "" : value.toString(), obj, workbook);
			}
		}
	}

	/**
	 * ��������ȡ??
	 * 
	 * @param list
	 * @param t
	 * @return
	 * @throws Exception
	 */
	private static Object getFieldBySomeMethod(List<Method> list, Object t) throws Exception {
		for (Method m : list) {
			if (t == null) {
				t = "";
				break;
			}
			t = m.invoke(t, new Object[] {});
		}
		return t;
	}

	/**
	 * �����ı����͵�Cell
	 * 
	 * @param row
	 * @param index
	 * @param text
	 * @param style
	 * @param entity
	 * @param workbook
	 */
	private static void createStringCell(Row row, int index, String text, ExportField entity, Workbook workbook) {
		Cell cell = row.createCell(index);
		switch (entity.getType()) {
		case 1:
			RichTextString Rtext = workbook instanceof HSSFWorkbook ? new HSSFRichTextString(text) : new XSSFRichTextString(text);
			cell.setCellValue(Rtext);
			break;
		case 2:
			cell.setCellType(Cell.CELL_TYPE_FORMULA);
			cell.setCellFormula(entity.getCellFormula());
			break;
		}
	}

	/**
	 * ͼƬ���͵�Cell
	 * 
	 * @param patriarch
	 * 
	 * @param entity
	 * @param row
	 * @param i
	 * @param string
	 * @param obj
	 * @param workbook
	 * @throws Exception
	 */
	private static void createImageCell(Drawing patriarch, ExportField entity, Row row, int i, String field, Object obj, Workbook workbook)
			throws Exception {
		if (StringUtils.isEmpty(field)) {
			return;
		}
		row.setHeight((short) (50 * entity.getHeight()));
		row.createCell(i);
		ClientAnchor anchor = workbook instanceof HSSFWorkbook ? new HSSFClientAnchor(0, 0, 0, 0, (short) i, row.getRowNum(), (short) (i + 1),
				row.getRowNum() + 1) : new XSSFClientAnchor(0, 0, 0, 0, (short) i, row.getRowNum(), (short) (i + 1), row.getRowNum() + 1);
		if (entity.getExportImageType() == 1) {
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			BufferedImage bufferImg;
			try {
				String path = ExcelExportOfTemplateUtil.class.getClassLoader().getResource("") + field;
				path = path.replace("WEB-INF/classes/", "");
				path = path.replace("file:/", "");
				bufferImg = ImageIO.read(new File(path));
				ImageIO.write(bufferImg, field.substring(field.indexOf(".") + 1, field.length()), byteArrayOut);
				patriarch.createPicture(anchor, row.getSheet().getWorkbook().addPicture(byteArrayOut.toByteArray(), Workbook.PICTURE_TYPE_JPEG));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			byte[] value = (byte[]) (entity.getGetMethods() != null ? getFieldBySomeMethod(entity.getGetMethods(), obj) : entity.getGetMethod().invoke(obj,
					new Object[] {}));
			if (value != null) {
				patriarch.createPicture(anchor, row.getSheet().getWorkbook().addPicture(value, Workbook.PICTURE_TYPE_JPEG));
			}
		}

	}

	/**
	 * ��ȡ????������ȫ����??
	 * 
	 * @param targetId
	 *            Ŀ��ID
	 * @param filed
	 * @throws Exception
	 */
	private static void getAllExcelField(String targetId, Field[] fields, List<ExportField> excelParams, Class<?> pojoClass, List<Method> getMethods)
			throws Exception {
		// ��������filed
		ExportField excelEntity;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			// ���ж��ǲ���collection,���ж��ǲ���java�Դ�����,֮����������Լ��Ķ�����
			if (ExcelPublicUtil.isNotUserExcelUserThis(field, targetId)) {
				continue;
			}
			if (ExcelPublicUtil.isCollection(field.getType())) {
				ExcelCollection excel = field.getAnnotation(ExcelCollection.class);
				ParameterizedType pt = (ParameterizedType) field.getGenericType();
				Class<?> clz = (Class<?>) pt.getActualTypeArguments()[0];
				List<ExportField> list = new ArrayList<ExportField>();
				getExcelFieldList(targetId, ExcelPublicUtil.getClassFields(clz), clz, list, null);
				excelEntity = new ExportField();
				excelEntity.setName(getExcelName(excel.exportName(), targetId));
				excelEntity.setOrderNum(getCellOrder(excel.orderNum(), targetId));
				excelEntity.setGetMethod(ExcelPublicUtil.getMethod(field.getName(), pojoClass, false));
				excelEntity.setList(list);
				excelParams.add(excelEntity);
			} else if (ExcelPublicUtil.isJavaClass(field)) {
				Excel excel = field.getAnnotation(Excel.class);
				excelEntity = new ExportField();
				excelEntity.setType(excel.exportType());
				getExcelField(targetId, field, excelEntity, excel, pojoClass);
				if (getMethods != null) {
					List<Method> newMethods = new ArrayList<Method>();
					newMethods.addAll(getMethods);
					newMethods.add(excelEntity.getGetMethod());
					excelEntity.setGetMethods(newMethods);
				}
				excelParams.add(excelEntity);
			} else {
				List<Method> newMethods = new ArrayList<Method>();
				if (getMethods != null) {
					newMethods.addAll(getMethods);
				}
				newMethods.add(ExcelPublicUtil.getMethod(field.getName(), pojoClass, false));
				getAllExcelField(targetId, ExcelPublicUtil.getClassFields(field.getType()), excelParams, field.getType(), newMethods);
			}
		}
	}

	/**
	 * �ж��������Ԫ����ʾ����??
	 * 
	 * @param exportName
	 * @param targetId
	 * @return
	 */
	private static String getExcelName(String exportName, String targetId) {
		if (exportName.indexOf(",") < 0) {
			return exportName;
		}
		String[] arr = exportName.split(",");
		for (String str : arr) {
			if (str.indexOf(targetId) != -1) {
				return str.split("_")[0];
			}
		}
		return null;
	}

	/**
	 * ��ȡ����ֶε�˳??
	 * 
	 * @param orderNum
	 * @param targetId
	 * @return
	 */
	private static int getCellOrder(String orderNum, String targetId) {
		if (isInteger(orderNum) || targetId == null) {
			return Integer.valueOf(orderNum);
		}
		String[] arr = orderNum.split(",");
		for (String str : arr) {
			if (str.indexOf(targetId) != -1) {
				return Integer.valueOf(str.split("_")[0]);
			}
		}
		return 0;
	}

	/**
	 * �ж��ַ����Ƿ�������
	 */
	private static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 
	 * @param targetId
	 * @param fields
	 * @param pojoClass
	 * @param list
	 * @param getMethods
	 * @throws Exception
	 */
	private static void getExcelFieldList(String targetId, Field[] fields, Class<?> pojoClass, List<ExportField> list, List<Method> getMethods)
			throws Exception {
		ExportField excelEntity;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (ExcelPublicUtil.isNotUserExcelUserThis(field, targetId)) {
				continue;
			}
			if (ExcelPublicUtil.isJavaClass(field)) {
				Excel excel = field.getAnnotation(Excel.class);
				excelEntity = new ExportField();
				getExcelField(targetId, field, excelEntity, excel, pojoClass);
				if (getMethods != null) {
					List<Method> newMethods = new ArrayList<Method>();
					newMethods.addAll(getMethods);
					newMethods.add(excelEntity.getGetMethod());
					excelEntity.setGetMethods(newMethods);
				}
				list.add(excelEntity);
			} else {
				List<Method> newMethods = new ArrayList<Method>();
				if (getMethods != null) {
					newMethods.addAll(getMethods);
				}
				newMethods.add(ExcelPublicUtil.getMethod(field.getName(), pojoClass, false));
				getExcelFieldList(targetId, ExcelPublicUtil.getClassFields(field.getType()), field.getType(), list, newMethods);
			}
		}
	}

	/**
	 * 
	 * @param targetId
	 * @param field
	 * @param excelEntity
	 * @param excel
	 * @param pojoClass
	 * @throws Exception
	 */
	private static void getExcelField(String targetId, Field field, ExportField excelEntity, Excel excel, Class<?> pojoClass) throws Exception {
		excelEntity.setName(getExcelName(excel.exportName(), targetId));
		excelEntity.setWidth(excel.exportFieldWidth());
		excelEntity.setHeight(excel.exportFieldHeight());
		excelEntity.setNeedMerge(excel.needMerge());
		excelEntity.setOrderNum(getCellOrder(excel.orderNum(), targetId));
		excelEntity.setWrap(excel.isWrap());
		excelEntity.setExportImageType(excel.imageType());
		excelEntity.setType(excel.exportType());
		excelEntity.setCellFormula(excel.cellFormula());
		String fieldname = field.getName();
		excelEntity.setGetMethod(ExcelPublicUtil.getMethod(fieldname, pojoClass, false));
		if (excel.exportConvertSign() == 1 || excel.imExConvert() == 1) {
			StringBuffer getConvertMethodName = new StringBuffer("convertGet");
			getConvertMethodName.append(fieldname.substring(0, 1).toUpperCase());
			getConvertMethodName.append(fieldname.substring(1));
			Method getConvertMethod = pojoClass.getMethod(getConvertMethodName.toString(), new Class[] {});
			excelEntity.setGetMethod(getConvertMethod);
		}
	}
}
