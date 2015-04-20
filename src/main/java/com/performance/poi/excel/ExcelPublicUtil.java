package com.performance.poi.excel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import com.performance.poi.excel.annotation.Excel;
import com.performance.poi.excel.annotation.ExcelCollection;
import com.performance.poi.excel.annotation.ExcelEntity;
import com.performance.poi.excel.annotation.ExcelIgnore;

public class ExcelPublicUtil {

	public final static String GET = "get";
	public final static String SET = "set";
	public final static String CONVERT_GET = "convertGet";

	/**
	 * ��ȡ��??���༰��????�ֽӿ���������������??
	 */
	public static Field[] getClassFields(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields;
		do {
			fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				list.add(fields[i]);
			}
			clazz = clazz.getSuperclass();
		} while (clazz != Object.class && clazz != null);
		return list.toArray(fields);
	}

	/**
	 * �ж��ǲ��Ǽ��ϵ�ʵ��??
	 */
	public static boolean isCollection(Class<?> clazz) {
		//TODO ò�ƺ���д������ ֮�����о���
		return clazz.isAssignableFrom(List.class) || clazz.isAssignableFrom(Set.class) || clazz.isAssignableFrom(Collection.class);
	}

	/**
	 * �ж��Ƿ�Ҫ�����excel����??
	 * 
	 * @param field
	 * @param targetId
	 * @return
	 */
	public static boolean isNotUserExcelUserThis(Field field, String targetId) {
		boolean boo = true;
		if (field.getAnnotation(ExcelIgnore.class) != null) {
			boo = true;
		} else if (boo && field.getAnnotation(ExcelCollection.class) != null && isUseInThis(field.getAnnotation(ExcelCollection.class).exportName(), targetId)) {
			boo = false;
		} else if (boo && field.getAnnotation(Excel.class) != null && isUseInThis(field.getAnnotation(Excel.class).exportName(), targetId)) {
			boo = false;
		} else if (boo && field.getAnnotation(ExcelEntity.class) != null && isUseInThis(field.getAnnotation(ExcelEntity.class).exportName(), targetId)) {
			boo = false;
		}
		return boo;
	}

	/**
	 * �ж��ǲ���ʹ??
	 * 
	 * @param exportName
	 * @param targetId
	 * @return
	 */
	private static boolean isUseInThis(String exportName, String targetId) {
		return targetId == null || exportName.equals("") || exportName.indexOf("_") < 0 || exportName.indexOf(targetId) != -1;
	}

	/**
	 * �ǲ���java����??
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isJavaClass(Field field) {
		Class<?> fieldType = field.getType();
		boolean isBaseClass = false;
		if (fieldType.isArray()) {
			isBaseClass = false;
		} else if (fieldType.isPrimitive() || fieldType.getPackage() == null || fieldType.getPackage().getName().equals("java.lang")
				|| fieldType.getPackage().getName().equals("java.math") || fieldType.getPackage().getName().equals("java.util")) {
			isBaseClass = true;
		}
		return isBaseClass;
	}

	/**
	 * ���״���????����
	 * 
	 * @param clazz
	 * @return
	 */
	public static Object createObject(Class<?> clazz, String targetId) {
		Object obj = null;
		String fieldname;
		Method setMethod;
		try {
			obj = clazz.newInstance();
			Field[] fields = getClassFields(clazz);
			for (Field field : fields) {
				if (isNotUserExcelUserThis(field, targetId)) {
					continue;
				}
				if (isCollection(field.getType())) {
					ExcelCollection collection = field.getAnnotation(ExcelCollection.class);
					fieldname = field.getName();
					setMethod = getMethod(fieldname, clazz, field.getType());
					setMethod.invoke(obj, ExcelPublicUtil.class.getClassLoader().loadClass(collection.type()).newInstance());
				} else if (!isJavaClass(field)) {
					fieldname = field.getName();
					setMethod = getMethod(fieldname, clazz, field.getType());
					setMethod.invoke(obj, createObject(field.getType(), targetId));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;

	}

	/**
	 * ���ض�Ӧ�ֶε�get����
	 * 
	 * @param fieldName �ֶ�??
	 * @param pojoClass Ҫ������ʵ���Class����
	 * @return ���ֶζ�Ӧ��get�ķ�����name->getName??
	 * @throws Exception
	 */
	public static Method getMethod(String fieldName, Class<?> pojoClass, boolean isConverted) throws Exception {
		StringBuffer getConvertMethodName = isConverted ? new StringBuffer(CONVERT_GET) : new StringBuffer(GET);
		getConvertMethodName.append(fieldName.substring(0, 1).toUpperCase());
		getConvertMethodName.append(fieldName.substring(1));
		return pojoClass.getMethod(getConvertMethodName.toString(), new Class[] {});
	}

	/**
	 * ��ȡ����
	 * 
	 * @param name
	 * @param pojoClass
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static Method getMethod(String name, Class<?> pojoClass, Class<?> type) throws Exception {
		StringBuffer getMethodName = new StringBuffer(SET);
		getMethodName.append(name.substring(0, 1).toUpperCase());
		getMethodName.append(name.substring(1));
		return pojoClass.getMethod(getMethodName.toString(), new Class[] { type });
	}

	/**
	 * ��ȡͼƬ����
	 */
	public static String getFileExtendName(byte[] photoByte) {
		String strFileExtendName = "JPG";
		if ((photoByte[0] == 71) && (photoByte[1] == 73) && (photoByte[2] == 70) && (photoByte[3] == 56) && ((photoByte[4] == 55) || (photoByte[4] == 57))
				&& (photoByte[5] == 97)) {
			strFileExtendName = "GIF";
		} else if ((photoByte[6] == 74) && (photoByte[7] == 70) && (photoByte[8] == 73) && (photoByte[9] == 70)) {
			strFileExtendName = "JPG";
		} else if ((photoByte[0] == 66) && (photoByte[1] == 77)) {
			strFileExtendName = "BMP";
		} else if ((photoByte[1] == 80) && (photoByte[2] == 78) && (photoByte[3] == 71)) {
			strFileExtendName = "PNG";
		}
		return strFileExtendName;
	}

	/**
	 * ��ȡExcel2003ͼƬ
	 * 
	 * @param sheet
	 *            ��ǰsheet����
	 * @param workbook
	 *            ��������??
	 * @return Map key:ͼƬ��Ԫ��������1_1��String��value:ͼƬ��PictureData
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, PictureData> getSheetPictrues03(HSSFSheet sheet, HSSFWorkbook workbook) {
		Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
		List<HSSFPictureData> pictures = workbook.getAllPictures();
		if (pictures.size() != 0) {
			for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
				HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
				if (shape instanceof HSSFPicture) {
					HSSFPicture pic = (HSSFPicture) shape;
					int pictureIndex = pic.getPictureIndex() - 1;
					HSSFPictureData picData = pictures.get(pictureIndex);
					String picIndex = String.valueOf(anchor.getRow1()) + "_" + String.valueOf(anchor.getCol1());
					sheetIndexPicMap.put(picIndex, picData);
				}
			}
			return sheetIndexPicMap;
		} else {
			return (Map<String, PictureData>) sheetIndexPicMap.put(null, null);
		}
	}

	/**
	 * ��ȡExcel2007ͼƬ
	 * 
	 * @param sheetNum
	 *            ��ǰsheet���
	 * @param sheet
	 *            ��ǰsheet����
	 * @param workbook
	 *            ��������??
	 * @return Map key:ͼƬ��Ԫ��������1_1��String��value:ͼƬ��PictureData
	 */
	public static Map<String, PictureData> getSheetPictrues07(XSSFSheet sheet, XSSFWorkbook workbook) {
		Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
		for (POIXMLDocumentPart dr : sheet.getRelations()) {
			if (dr instanceof XSSFDrawing) {
				XSSFDrawing drawing = (XSSFDrawing) dr;
				List<XSSFShape> shapes = drawing.getShapes();
				for (XSSFShape shape : shapes) {
					XSSFPicture pic = (XSSFPicture) shape;
					XSSFClientAnchor anchor = pic.getPreferredSize();
					CTMarker ctMarker = anchor.getFrom();
					String picIndex = ctMarker.getRow() + "_" + ctMarker.getCol();
					sheetIndexPicMap.put(picIndex, pic.getPictureData());
				}
			}
		}
		return sheetIndexPicMap;
	}
	
	public static String getAbsolutePath(String rpath){
		String path = "";
		try {
			path = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
			path = path.replace("WEB-INF/classes/", "");
			path = path.replace("file:/", "") + rpath;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return path;
	}
}
