package com.performance.poi.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.performance.poi.excel.annotation.Excel;
import com.performance.poi.excel.annotation.ExcelTarget;
import com.performance.poi.excel.entity.ExcelCollectionParams;
import com.performance.poi.excel.entity.ImportField;
import com.performance.poi.excel.entity.ImportParams;

/**
 * Excel ���빤����
 */
public final class ExcelImportUtil {

	/**
	 * Excel ���� �ֶ����� Integer,Long,Double,Date,String,Boolean,BigDecimal
	 */
	public static Collection<?> importExcel(File file, Class<?> pojoClass, ImportParams params) {
		FileInputStream in = null;
		Collection<?> result = null;
		try {
			in = new FileInputStream(file);
			result = importExcelByIs(in, pojoClass, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Excel ���� field �ֶ����� Integer,Long,Double,Date,String,Boolean,BigDecimal
	 */
	public static Collection<?> importExcelByIs(InputStream inputstream, Class<?> pojoClass, ImportParams params) throws Exception {
		Collection<T> result = new ArrayList<T>();
		Workbook book = null;
		boolean isXSSFWorkbook = true;
		if (!(inputstream.markSupported())) {
			inputstream = new PushbackInputStream(inputstream, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(inputstream)) {
			book = new HSSFWorkbook(inputstream);
			isXSSFWorkbook = false;
		} else if (POIXMLDocument.hasOOXMLHeader(inputstream)) {
			book = new XSSFWorkbook(OPCPackage.open(inputstream));
		}
		Map<String, PictureData> pictures;
		for (int i = 0; i < params.getSheetNum(); i++) {
			if (isXSSFWorkbook) {
				pictures = ExcelPublicUtil.getSheetPictrues07((XSSFSheet) book.getSheetAt(i), (XSSFWorkbook) book);
			} else {
				pictures = ExcelPublicUtil.getSheetPictrues03((HSSFSheet) book.getSheetAt(i), (HSSFWorkbook) book);
			}
			result.addAll(importExcel(result, book.getSheetAt(i), pojoClass, params, pictures));
		}
		if (params.isNeedSave()) {
			saveThisExcel(params, pojoClass, isXSSFWorkbook, book);
		}
		return result;
	}

	/**
	 * �����Excel�ļ�
	 */
	private static void saveThisExcel(ImportParams params, Class<?> pojoClass, boolean isXSSFWorkbook, Workbook book) throws Exception {
		String path = ExcelPublicUtil.getAbsolutePath(getSaveExcelUrl(params, pojoClass));
		File savefile = new File(path);
		if (!savefile.exists()) {
			savefile.mkdirs();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyMMddHHmmss");
		FileOutputStream fos = new FileOutputStream(path + "/" + format.format(new Date()) + "_" + Math.round(Math.random() * 100000)
				+ (isXSSFWorkbook == true ? ".xlsx" : ".xls"));
		book.write(fos);
		fos.close();
	}

	/**
	 * ��ȡ�����Excel�ľ���·��
	 */
	private static String getSaveExcelUrl(ImportParams params, Class<?> pojoClass) throws Exception {
		String url = "";
		if (params.getSaveUrl().equals("upload/excelUpload")) {
			String[] tmps = pojoClass.getName().split("\\.");
			url = tmps[tmps.length - 1];
			return params.getSaveUrl() + "/" + url.substring(0, url.lastIndexOf("Entity"));
		}
		return params.getSaveUrl();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Collection<? extends T> importExcel(Collection<T> result, Sheet sheet, Class<?> pojoClass, ImportParams params,
			Map<String, PictureData> pictures) throws Exception {
		Collection collection = new ArrayList();
		Map<String, ImportField> excelParams = new HashMap<String, ImportField>();
		List<ExcelCollectionParams> excelCollection = new ArrayList<ExcelCollectionParams>();
		Field fileds[] = ExcelPublicUtil.getClassFields(pojoClass);
		ExcelTarget etarget = pojoClass.getAnnotation(ExcelTarget.class);
		String targetId = null;
		if (etarget != null) {
			targetId = etarget.id();
		}
		getAllExcelField(targetId, fileds, excelParams, excelCollection, pojoClass, null);
		Iterator<Row> rows = sheet.rowIterator();
		for (int j = 0; j < params.getTitleRows(); j++) {
			rows.next();
		}
		Row row = null;
		Iterator<Cell> cellTitle;
		Map<Integer, String> titlemap = new HashMap<Integer, String>();
		for (int j = 0; j < params.getSecondTitleRows(); j++) {
			row = rows.next();
			cellTitle = row.cellIterator();
			int i = row.getFirstCellNum();
			while (cellTitle.hasNext()) {
				Cell cell = cellTitle.next();
				String value = cell.getStringCellValue();
				if (!StringUtils.isEmpty(value)) {
					titlemap.put(i, value);
				}
				i = i + 1;
			}
		}
		Object object = null;
		String picId;
		while (rows.hasNext()) {
			row = rows.next();
			// �ж��ǲ��Ǽ���Ԫ�أ�����Ǿͼ��������������,���Ǿʹ����µĶ���
			if ((row.getCell(params.getKeyIndex()) == null || StringUtils.isEmpty(getKeyValue(row.getCell(params.getKeyIndex())))) && object != null) {
				for (ExcelCollectionParams param : excelCollection) {
					addListContinue(object, param, row, titlemap, targetId, pictures, params);
				}
			} else {
				object = ExcelPublicUtil.createObject(pojoClass, targetId);
				for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
					Cell cell = row.getCell(i);
					String titleString = (String) titlemap.get(i);
					if (excelParams.containsKey(titleString)) {
						if (excelParams.get(titleString).getType() == 2) {
							picId = row.getRowNum() + "_" + i;
							saveImage(object, picId, excelParams, titleString, pictures, params);
						} else {
							judgeTypeAndSetValue(object, cell, excelParams, titleString);
						}
					}
				}
				for (ExcelCollectionParams param : excelCollection) {
					addListContinue(object, param, row, titlemap, targetId, pictures, params);
				}
				collection.add(object);
			}
		}
		return collection;
	}

	/**
	 * ��ȡkey��??,��Բ�ͬ���ͻ�ȡ��ͬ��??
	 */
	private static String getKeyValue(Cell cell) {
		Object obj = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			obj = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			obj = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			obj = cell.getNumericCellValue();
			break;
		}
		return obj == null ? null : obj.toString();
	}

	/**
	 * 
	 * @param object
	 * @param cell
	 * @param excelParams
	 * @param titleString
	 * @param pictures
	 * @param currentImageIndex
	 * @param params
	 * @throws Exception
	 */
	private static void saveImage(Object object, String picId, Map<String, ImportField> excelParams, String titleString,
			Map<String, PictureData> pictures, ImportParams params) throws Exception {
		if (pictures == null) {
			return;
		}
		PictureData image = pictures.get(picId);
		byte[] data = image.getData();
		String fileName = "pic" + Math.round(Math.random() * 100000000000L);
		fileName += "." + ExcelPublicUtil.getFileExtendName(data);
		if (excelParams.get(titleString).getSaveType() == 1) {
			String path = ExcelPublicUtil.getAbsolutePath(getSaveUrl(excelParams.get(titleString), object));
			File savefile = new File(path);
			if (!savefile.exists()) {
				savefile.mkdirs();
			}
			savefile = new File(path + "/" + fileName);
			
			OutputStream outputStream = new FileOutputStream(savefile);
			outputStream.write(data);
			outputStream.close();
			
			setValues(excelParams.get(titleString), object, getSaveUrl(excelParams.get(titleString), object) + "/" + fileName);
		} else {
			setValues(excelParams.get(titleString), object, data);
		}
	}

	/**
	 * ��ȡ�������ʵ·��
	 * 
	 * @param excelImportEntity
	 * @param object
	 * @return
	 * @throws Exception
	 */
	private static String getSaveUrl(ImportField excelImportEntity, Object object) throws Exception {
		String url = "";
		if (excelImportEntity.getSaveUrl().equals("upload")) {
			if (excelImportEntity.getSetMethods() != null && excelImportEntity.getSetMethods().size() > 0) {
				object = getFieldBySomeMethod(excelImportEntity.getSetMethods(), object);
			}
			url = object.getClass().getName().split("\\.")[object.getClass().getName().split("\\.").length - 1];
			return excelImportEntity.getSaveUrl() + "/" + url.substring(0, url.lastIndexOf("Entity"));
		}
		return excelImportEntity.getSaveUrl();
	}

	/***
	 * ��List����������Ԫ��
	 * 
	 * @param object
	 * @param param
	 * @param row
	 * @param titlemap
	 * @param targetId
	 * @param params
	 * @param currentImageIndex
	 * @param pictures
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addListContinue(Object object, ExcelCollectionParams param, Row row, Map<Integer, String> titlemap, String targetId,
			Map<String, PictureData> pictures, ImportParams params) throws Exception {
		Collection collection = (Collection) ExcelPublicUtil.getMethod(param.getName(), object.getClass(), false).invoke(object, new Object[] {});
		Object entity = ExcelPublicUtil.createObject(param.getType(), targetId);
		String picId;
		boolean isUsed = false;// �Ƿ�????�����������
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			String titleString = (String) titlemap.get(i);
			if (param.getExcelParams().containsKey(titleString)) {
				if (param.getExcelParams().get(titleString).getType() == 2) {
					picId = row.getRowNum() + "_" + i;
					saveImage(object, picId, param.getExcelParams(), titleString, pictures, params);
				} else {
					judgeTypeAndSetValue(entity, cell, param.getExcelParams(), titleString);
				}
				isUsed = true;
			}
		}
		if (isUsed) {
			collection.add(entity);
		}
	}

	/**
	 * ����??
	 * 
	 * @param object
	 * @param excelParams
	 * @param cell
	 * @param titleString
	 */
	private static void judgeTypeAndSetValue(Object object, Cell cell, Map<String, ImportField> excelParams, String titleString) throws Exception {
		ImportField entity = excelParams.get(titleString);
		Method setMethod = entity.getSetMethods() != null && entity.getSetMethods().size() > 0 ? entity.getSetMethods().get(entity.getSetMethods().size() - 1)
				: entity.getSetMethod();
		Type[] ts = setMethod.getGenericParameterTypes();
		String xclass = ts[0].toString();
		if (xclass.equals("class java.lang.String")) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			setValues(entity, object, cell.getStringCellValue());
		} else if (xclass.equals("class java.util.Date")) {
			Date cellDate = null;
			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				// ���ڸ�ʽ
				cellDate = cell.getDateCellValue();
				setValues(entity, object, cellDate);
			} else {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cellDate = getDateData(entity, cell.getStringCellValue());
				setValues(entity, object, cellDate);
			}
		} else if (xclass.equals("class java.lang.Boolean")) {
			boolean valBool;
			if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
				valBool = cell.getBooleanCellValue();
			} else {
				valBool = cell.getStringCellValue().equalsIgnoreCase("true") || (!cell.getStringCellValue().equals("0"));
			}
			setValues(entity, object, valBool);
		} else if (xclass.equals("class java.lang.Integer")) {
			Integer valInt;
			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				valInt = (new Double(cell.getNumericCellValue())).intValue();
			} else {
				valInt = new Integer(cell.getStringCellValue());
			}
			setValues(entity, object, valInt);
		} else if (xclass.equals("class java.lang.Long")) {
			Long valLong;
			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				valLong = (new Double(cell.getNumericCellValue())).longValue();
			} else {
				valLong = new Long(cell.getStringCellValue());
			}
			setValues(entity, object, valLong);
		} else if (xclass.equals("class java.math.BigDecimal")) {
			BigDecimal valDecimal;
			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				valDecimal = new BigDecimal(cell.getNumericCellValue());
			} else {
				valDecimal = new BigDecimal(cell.getStringCellValue());
			}
			setValues(entity, object, valDecimal);
		} else if (xclass.equals("class java.lang.Double")) {
			Double valDouble;
			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				valDouble = new Double(cell.getNumericCellValue());
			} else {
				valDouble = new Double(cell.getStringCellValue());
			}
			setValues(entity, object, valDouble);
		}

	}

	/**
	 * ��ȡ������������
	 * 
	 * @param entity
	 * @param stringCellValue
	 * @return
	 */
	private static Date getDateData(ImportField entity, String value) {
		if (StringUtils.isNotEmpty(entity.getImportFormat()) && StringUtils.isNotEmpty(value)) {
			SimpleDateFormat format = new SimpleDateFormat(entity.getImportFormat());
			try {
				return format.parse(value);
			} catch (ParseException e) {
			}
		}
		return null;
	}

	/**
	 * 
	 * @param entity
	 * @param object
	 * @param value
	 * @throws Exception
	 */
	private static void setValues(ImportField entity, Object object, Object value) throws Exception {
		if (entity.getSetMethods() != null) {
			setFieldBySomeMethod(entity.getSetMethods(), object, value);
		} else {
			entity.getSetMethod().invoke(object, value);
		}
	}

	/**
	 * ���get ????��set
	 * 
	 * @param setMethods
	 * @param object
	 */
	private static void setFieldBySomeMethod(List<Method> setMethods, Object object, Object value) throws Exception {
		Object t = getFieldBySomeMethod(setMethods, object);
		setMethods.get(setMethods.size() - 1).invoke(t, value);
	}

	private static Object getFieldBySomeMethod(List<Method> list, Object t) throws Exception {
		Method m;
		for (int i = 0; i < list.size() - 1; i++) {
			m = list.get(i);
			t = m.invoke(t, new Object[] {});
		}
		return t;
	}

	/**
	 * ��ȡ????������ȫ����??
	 * 
	 * @param targetId
	 *            Ŀ��ID
	 * @param excelCollection
	 * @param filed
	 * @throws Exception
	 */
	private static void getAllExcelField(String targetId, Field[] fields, Map<String, ImportField> excelParams,
			List<ExcelCollectionParams> excelCollection, Class<?> pojoClass, List<Method> getMethods) throws Exception {
		ImportField excelEntity = null;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (ExcelPublicUtil.isNotUserExcelUserThis(field, targetId)) {
				continue;
			}
			if (ExcelPublicUtil.isCollection(field.getType())) {
				// ���϶���������??
				ExcelCollectionParams collection = new ExcelCollectionParams();
				collection.setName(field.getName());
				Map<String, ImportField> temp = new HashMap<String, ImportField>();
				ParameterizedType pt = (ParameterizedType) field.getGenericType();
				Class<?> clz = (Class<?>) pt.getActualTypeArguments()[0];
				collection.setType(clz);
				getExcelFieldList(targetId, ExcelPublicUtil.getClassFields(clz), clz, temp, null);
				collection.setExcelParams(temp);
				excelCollection.add(collection);
			} else if (ExcelPublicUtil.isJavaClass(field)) {
				addEntityToMap(targetId, field, excelEntity, pojoClass, getMethods, excelParams);
			} else {
				List<Method> newMethods = new ArrayList<Method>();
				if (getMethods != null) {
					newMethods.addAll(getMethods);
				}
				newMethods.add(ExcelPublicUtil.getMethod(field.getName(), pojoClass, false));
				getAllExcelField(targetId, ExcelPublicUtil.getClassFields(field.getType()), excelParams, excelCollection, field.getType(), newMethods);
			}
		}
	}

	private static void getExcelFieldList(String targetId, Field[] fields, Class<?> pojoClass, Map<String, ImportField> temp, List<Method> getMethods)
			throws Exception {
		ImportField excelEntity = null;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (ExcelPublicUtil.isNotUserExcelUserThis(field, targetId)) {
				continue;
			}
			if (ExcelPublicUtil.isJavaClass(field)) {
				addEntityToMap(targetId, field, excelEntity, pojoClass, getMethods, temp);
			} else {
				List<Method> newMethods = new ArrayList<Method>();
				if (getMethods != null) {
					newMethods.addAll(getMethods);
				}
				newMethods.add(ExcelPublicUtil.getMethod(field.getName(), pojoClass, field.getType()));
				getExcelFieldList(targetId, ExcelPublicUtil.getClassFields(field.getType()), field.getType(), temp, newMethods);
			}
		}
	}

	/**
	 * �����ע������ŵ����Ͷ�����
	 * 
	 * @param targetId
	 * @param field
	 * @param excelEntity
	 * @param excel
	 * @param pojoClass
	 * @param getMethods
	 * @param temp
	 * @throws Exception
	 */
	private static void addEntityToMap(String targetId, Field field, ImportField excelEntity, Class<?> pojoClass, List<Method> getMethods,
			Map<String, ImportField> temp) throws Exception {
		Excel excel = field.getAnnotation(Excel.class);
		excelEntity = new ImportField();
		excelEntity.setType(excel.exportType());
		excelEntity.setSaveUrl(excel.savePath());
		excelEntity.setSaveType(excel.imageType());
		getExcelField(targetId, field, excelEntity, excel, pojoClass);
		if (getMethods != null) {
			List<Method> newMethods = new ArrayList<Method>();
			newMethods.addAll(getMethods);
			newMethods.add(excelEntity.getSetMethod());
			excelEntity.setSetMethods(newMethods);
		}
		temp.put(excelEntity.getName(), excelEntity);

	}

	private static void getExcelField(String targetId, Field field, ImportField excelEntity, Excel excel, Class<?> pojoClass) throws Exception {
		excelEntity.setName(getExcelName(excel.exportName(), targetId));
		String fieldname = field.getName();
		if (excel.importConvertSign() == 1 || excel.imExConvert() == 1) {
			StringBuffer getConvertMethodName = new StringBuffer("convertSet");
			getConvertMethodName.append(fieldname.substring(0, 1).toUpperCase());
			getConvertMethodName.append(fieldname.substring(1));
			Method getConvertMethod = pojoClass.getMethod(getConvertMethodName.toString(), new Class[] { field.getType() });
			excelEntity.setSetMethod(getConvertMethod);
		} else {
			excelEntity.setSetMethod(ExcelPublicUtil.getMethod(fieldname, pojoClass, field.getType()));
		}
		if (StringUtils.isEmpty(excel.importFormat())) {
			excelEntity.setImportFormat(excel.imExFormat());
		} else {
			excelEntity.setImportFormat(excel.importFormat());
		}
	}

	/**
	 * �ж��������Ԫ����ʾ������
	 * 
	 * @param exportName
	 * @param targetId
	 * @return
	 */
	private static String getExcelName(String exportName, String targetId) {
		if (exportName.indexOf("_") < 0) {
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
}
