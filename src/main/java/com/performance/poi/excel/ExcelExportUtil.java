package com.performance.poi.excel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.performance.poi.excel.annotation.Excel;
import com.performance.poi.excel.annotation.ExcelCollection;
import com.performance.poi.excel.annotation.ExcelTarget;
import com.performance.poi.excel.entity.ComparatorExcelField;
import com.performance.poi.excel.entity.ExportField;
import com.performance.poi.excel.entity.ExcelTitle;
import com.performance.poi.excel.entity.TemplateExportParams;

/**
 * excel导出工具类
 */
public final class ExcelExportUtil {

	/**
	 * 一个excel多个sheet
	 * 
	 * @param list
	 *            每个Map中key title对应表格Title, key entity对应实体, key
	 *            data对应Collection数据
	 * @return
	 */
	public static HSSFWorkbook exportExcel(List<Map<String, Object>> list) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		for (Map<String, Object> map : list) {
			createSheet(workbook, (ExcelTitle) map.get("title"), (Class<?>) map.get("entity"), (Collection<?>) map.get("data"));
		}
		return workbook;
	}

	/**
	 * @param title
	 *            表格标题属性
	 * @param pojoClass
	 *            Excel对象Class
	 * @param dataSet
	 *            Excel对象数据List
	 */
	public static HSSFWorkbook exportExcel(ExcelTitle title, Class<?> pojoClass, Collection<?> dataSet) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		createSheet(workbook, title, pojoClass, dataSet);
		return workbook;
	}

	/**
	 * 导出文件通过模板解析
	 * 
	 * @param entity
	 *            导出参数??
	 * @param pojoClass
	 *            对应实体
	 * @param dataSet
	 *            实体集合
	 * @param map
	 *            模板集合
	 * @return
	 */
	public static Workbook exportExcel(TemplateExportParams params, Class<?> pojoClass, Collection<?> dataSet, Map<String, Object> map) {
		return ExcelExportOfTemplateUtil.exportExcel(params, pojoClass, dataSet, map);
	}

	/**
	 * 导出文件通过模板解析只有模板,没有集合
	 */
	public static Workbook exportExcel(TemplateExportParams params, Map<String, Object> map) {
		return ExcelExportOfTemplateUtil.exportExcel(params, null, null, map);
	}

	private static void createSheet(HSSFWorkbook workbook, ExcelTitle title, Class<?> pojoClass, Collection<?> dataSet) {
		try {
			Sheet sheet = workbook.createSheet(title.getSheetName());
			// 创建表格属性
			Map<String, HSSFCellStyle> styles = createStyles(workbook);
			Drawing patriarch = sheet.createDrawingPatriarch();
			List<ExportField> excelParams = new ArrayList<ExportField>();
			// 得到所有字段
			Field fileds[] = ExcelPublicUtil.getClassFields(pojoClass);
			ExcelTarget etarget = pojoClass.getAnnotation(ExcelTarget.class);
			String targetId = null;
			if (etarget != null) {
				targetId = etarget.id();
			}
			getAllExcelField(targetId, fileds, excelParams, pojoClass, null);
			sortAllParams(excelParams);
			int index = 0;
			int feildWidth = getFieldWidth(excelParams);
			if (title.getTitle() != null) {
				int i = createHeaderRow(title, sheet, workbook, feildWidth);
				sheet.createFreezePane(0, 2 + i, 0, 2 + i);
				index += i;
			} else {
				sheet.createFreezePane(0, 2, 0, 2);
			}
			createTitleRow(title, sheet, workbook, index, excelParams);
			index += 2;
			setCellWith(excelParams, sheet);
			Iterator<?> its = dataSet.iterator();
			while (its.hasNext()) {
				Object t = its.next();
				index += createCells(patriarch, index, t, excelParams, sheet, workbook, styles);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对字段根据用户设置排??
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
	 * 创建 Cells
	 * 
	 * @param styles
	 * @throws Exception
	 */
	private static int createCells(Drawing patriarch, int index, Object t, List<ExportField> excelParams, Sheet sheet, HSSFWorkbook workbook,
			Map<String, HSSFCellStyle> styles) throws Exception {
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
					createListCells(patriarch, index + listC, cellNum, obj, entity.getList(), sheet, workbook, styles);
					listC++;
				}
				cellNum += entity.getList().size();
				if (list != null && list.size() > maxHeight) {
					maxHeight = list.size();
				}
			} else {
				Object value = getCellValue(entity, t);
				if (entity.getType() == 1) {
					createStringCell(row, cellNum++, value == null ? "" : value.toString(), index % 2 == 0 ? getStyles(styles, false, entity.isWrap())
							: getStyles(styles, true, entity.isWrap()), entity);
				} else {
					createImageCell(patriarch, entity, row, cellNum++, value == null ? "" : value.toString(), t);
				}
			}
		}
		// 合并需要合并的单元格
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
	 * 获取填如这个cell的??,提供????附加功能
	 * 
	 * @param entity
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private static Object getCellValue(ExportField entity, Object obj) throws Exception {
		Object value = entity.getGetMethods() != null ? getFieldBySomeMethod(entity.getGetMethods(), obj) : entity.getGetMethod().invoke(obj, new Object[] {});
		// step 1 判断是不是日??????????格式??
		if (StringUtils.isNotEmpty(entity.getExportFormat())) {
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
	 * 创建List之后的各个Cells
	 * 
	 * @param styles
	 */
	private static void createListCells(Drawing patriarch, int index, int cellNum, Object obj, List<ExportField> excelParams, Sheet sheet,
			HSSFWorkbook workbook, Map<String, HSSFCellStyle> styles) throws Exception {
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
			if (entity.getType() == 1) {
				createStringCell(row, cellNum++, value == null ? "" : value.toString(), row.getRowNum() % 2 == 0 ? getStyles(styles, false, entity.isWrap())
						: getStyles(styles, true, entity.isWrap()), entity);
			} else {
				createImageCell(patriarch, entity, row, cellNum++, value == null ? "" : value.toString(), obj);
			}
		}
	}

	/**
	 * 多个反射获取??
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

	private static void setCellWith(List<ExportField> excelParams, Sheet sheet) {
		int index = 0;
		for (int i = 0; i < excelParams.size(); i++) {
			if (excelParams.get(i).getList() != null) {
				List<ExportField> list = excelParams.get(i).getList();
				for (int j = 0; j < list.size(); j++) {
					sheet.setColumnWidth(index, 256 * list.get(j).getWidth());
					index++;
				}
			} else {
				sheet.setColumnWidth(index, 256 * excelParams.get(i).getWidth());
				index++;
			}
		}
	}

	/**
	 * 创建表头
	 * 
	 * @param entity
	 * 
	 * @param index
	 */
	private static void createTitleRow(ExcelTitle title, Sheet sheet, HSSFWorkbook workbook, int index, List<ExportField> excelParams) {
		Row row = sheet.createRow(index);
		Row row1 = sheet.createRow(index + 1);
		row.setHeight((short) 450);
		int cellIndex = 0;
		CellStyle titleStyle = getTitleStyle(workbook, title);
		for (int i = 0, exportFieldTitleSize = excelParams.size(); i < exportFieldTitleSize; i++) {
			ExportField entity = excelParams.get(i);
			createStringCell(row, cellIndex, entity.getName(), titleStyle, entity);
			if (entity.getList() != null) {
				List<ExportField> sTitel = entity.getList();
				sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex, cellIndex + sTitel.size() - 1));
				for (int j = 0, size = sTitel.size(); j < size; j++) {
					createStringCell(row1, cellIndex, sTitel.get(j).getName(), titleStyle, entity);
					cellIndex++;
				}
			} else {
				sheet.addMergedRegion(new CellRangeAddress(index, index + 1, cellIndex, cellIndex));
				cellIndex++;
			}
		}

	}

	/**
	 * 创建文本类型的Cell
	 * 
	 * @param row
	 * @param index
	 * @param text
	 * @param style
	 * @param entity
	 */
	public static void createStringCell(Row row, int index, String text, CellStyle style, ExportField entity) {
		Cell cell = row.createCell(index);
		RichTextString Rtext = new HSSFRichTextString(text);
		cell.setCellValue(Rtext);
		cell.setCellStyle(style);
	}

	/**
	 * 图片类型的Cell
	 * 
	 * @param patriarch
	 * 
	 * @param entity
	 * @param row
	 * @param i
	 * @param string
	 * @param obj
	 * @throws Exception
	 */
	private static void createImageCell(Drawing patriarch, ExportField entity, Row row, int i, String string, Object obj) throws Exception {
		row.setHeight((short) (50 * entity.getHeight()));
		row.createCell(i);
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) i, row.getRowNum(), (short) (i + 1), row.getRowNum() + 1);
		if (StringUtils.isEmpty(string)) {
			return;
		}
		if (entity.getExportImageType() == 1) {
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			BufferedImage bufferImg;
			try {
				String path = ExcelPublicUtil.getAbsolutePath(string);
				bufferImg = ImageIO.read(new File(path));
				ImageIO.write(bufferImg, string.substring(string.indexOf(".") + 1, string.length()), byteArrayOut);
				byte[] value = byteArrayOut.toByteArray();
				patriarch.createPicture(anchor, row.getSheet().getWorkbook().addPicture(value, getImageType(value)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			byte[] value = (byte[]) (entity.getGetMethods() != null ? getFieldBySomeMethod(entity.getGetMethods(), obj) : entity.getGetMethod().invoke(obj,
					new Object[] {}));
			if (value != null) {
				patriarch.createPicture(anchor, row.getSheet().getWorkbook().addPicture(value, getImageType(value)));
			}
		}

	}

	/**
	 * 获取图片类型,设置图片插入类型
	 */
	private static int getImageType(byte[] value) {
		String type = ExcelPublicUtil.getFileExtendName(value);
		if (type.equalsIgnoreCase("JPG")) {
			return HSSFWorkbook.PICTURE_TYPE_JPEG;
		} else if (type.equalsIgnoreCase("PNG")) {
			return HSSFWorkbook.PICTURE_TYPE_PNG;
		}
		return HSSFWorkbook.PICTURE_TYPE_JPEG;
	}

	/**
	 * 创建 表头
	 * 
	 * @param title
	 * @param sheet
	 * @param workbook
	 * @param feildWidth
	 */
	public static int createHeaderRow(ExcelTitle entity, Sheet sheet, HSSFWorkbook workbook, int feildWidth) {
		Row row = sheet.createRow(0);
		row.setHeight((short) 900);
		createStringCell(row, 0, entity.getTitle(), getHeaderStyle(workbook, entity), null);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, feildWidth));
		if (entity.getSubTitle() != null) {
			row = sheet.createRow(1);
			HSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			createStringCell(row, 0, entity.getSubTitle(), style, null);
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, feildWidth));
			return 2;
		}
		return 1;
	}

	/**
	 * 获取导出报表的字段??长度
	 * 
	 * @param exportFieldTitle
	 * @param secondTitle
	 * @return
	 */
	private static int getFieldWidth(List<ExportField> excelParams) {
		int length = -1;// ??????计算单元格的
		for (ExportField entity : excelParams) {
			length += entity.getList() != null ? entity.getList().size() : 1;
		}
		return length;
	}

	/**
	 * 获取要导出的全部字段
	 * 
	 * @param targetId
	 *            目标ID
	 * @param filed
	 * @throws Exception
	 */
	private static void getAllExcelField(String targetId, Field[] fields, List<ExportField> excelParams, Class<?> pojoClass, List<Method> getMethods)
			throws Exception {
		// 遍历整个filed
		ExportField excelEntity;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			// 先判断是不是collection,在判断是不是java自带对象,之后就是我们自己的对象了
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
				excelEntity = getExportEntity(targetId, field, pojoClass);
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
	 * 判断在这个单元格显示的名??
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
	 * 获取这个字段的顺??
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
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 字段是集合类型时
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
				excelEntity = getExportEntity(targetId, field, pojoClass);
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
	 * @param targetId
	 * @param field
	 * @param excelEntity
	 * @param excel
	 * @param pojoClass
	 * @throws Exception
	 */
	public static ExportField getExportEntity(String targetId, Field field, Class<?> pojoClass) throws Exception {
		ExportField excelEntity = new ExportField();
		Excel excel = field.getAnnotation(Excel.class);

		excelEntity.setType(excel.exportType());
		excelEntity.setName(getExcelName(excel.exportName(), targetId));
		excelEntity.setWidth(excel.exportFieldWidth());
		excelEntity.setHeight(excel.exportFieldHeight());
		excelEntity.setNeedMerge(excel.needMerge());
		excelEntity.setOrderNum(getCellOrder(excel.orderNum(), targetId));
		excelEntity.setWrap(excel.isWrap());
		excelEntity.setExportImageType(excel.imageType());
		excelEntity.setExportFormat(StringUtils.isNotEmpty(excel.exportFormat()) ? excel.exportFormat() : excel.imExFormat());
		String fieldname = field.getName();
		excelEntity.setGetMethod(ExcelPublicUtil.getMethod(fieldname, pojoClass, false));
		if (excel.exportConvertSign() == 1 || excel.imExConvert() == 1) {
			excelEntity.setGetMethod(ExcelPublicUtil.getMethod(fieldname, pojoClass, true));
		}

		return excelEntity;
	}

	/**
	 * 字段说明的Style
	 * 
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook, ExcelTitle entity) {
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFillForegroundColor(entity.getHeaderColor()); // 填充的背景颜色
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充图案
		titleStyle.setWrapText(true);
		return titleStyle;
	}

	/**
	 * 表明的Style
	 * 
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook, ExcelTitle entity) {
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 18);
		titleStyle.setFont(font);
		titleStyle.setFillForegroundColor(entity.getColor());
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return titleStyle;
	}

	public static HSSFCellStyle getTwoStyle(HSSFWorkbook workbook, boolean isWarp) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderLeft((short) 1); // 左边
		style.setBorderRight((short) 1); // 右边
		style.setBorderBottom((short) 1);
		style.setBorderTop((short) 1);
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index); // 填充的背景颜
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充图案
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		if (isWarp) {
			style.setWrapText(true);
		}
		return style;
	}

	public static HSSFCellStyle getOneStyle(HSSFWorkbook workbook, boolean isWarp) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderLeft((short) 1); // 左边
		style.setBorderRight((short) 1); // 右边
		style.setBorderBottom((short) 1);
		style.setBorderTop((short) 1);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		if (isWarp) {
			style.setWrapText(true);
		}
		return style;
	}

	public static Map<String, HSSFCellStyle> createStyles(HSSFWorkbook workbook) {
		Map<String, HSSFCellStyle> map = new HashMap<String, HSSFCellStyle>();
		map.put("one", getOneStyle(workbook, false));
		map.put("oneWrap", getOneStyle(workbook, true));
		map.put("two", getTwoStyle(workbook, false));
		map.put("twoWrap", getTwoStyle(workbook, true));
		return map;
	}

	public static CellStyle getStyles(Map<String, HSSFCellStyle> map, boolean needOne, boolean isWrap) {
		if (needOne && isWrap) {
			return map.get("oneWrap");
		}
		if (needOne) {
			return map.get("one");
		}
		if (needOne == false && isWrap) {
			return map.get("twoWrap");
		}
		return map.get("two");
	}

}
