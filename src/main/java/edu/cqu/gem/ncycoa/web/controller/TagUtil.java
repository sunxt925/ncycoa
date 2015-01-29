package edu.cqu.gem.ncycoa.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

import edu.cqu.gem.common.dto.DataGrid;

public class TagUtil {

	/**
	 * 获取对象内对应字段的值
	 */
	public static Object fieldNametoValues(String FiledName, Object o) {
		
		Object value;
		String fieldName = "";
		String childFieldName = null;
		if (FiledName.indexOf("_") == -1) {
			if (FiledName.indexOf(".") == -1) {
				fieldName = FiledName;
			} else {
				fieldName = FiledName.substring(0, FiledName.indexOf("."));// 外键字段引用名
				childFieldName = FiledName.substring(FiledName.indexOf(".") + 1);// 外键字段名
			}
		} else {
			fieldName = FiledName.substring(0, FiledName.indexOf("_"));// 外键字段引用名
			childFieldName = FiledName.substring(FiledName.indexOf("_") + 1);// 外键字段名
		}
		
		try {
			value = PropertyUtils.getSimpleProperty(o, fieldName);
		} catch (Exception ex) {
			value = null;
		}
		
		if (value != null && !"".equals(value) && (FiledName.indexOf("_") != -1 || FiledName.indexOf(".") != -1)) {
			value = fieldNametoValues(childFieldName, value);
		}
		
		if (value != null && !"".equals(value)) {
			value = value.toString().trim();
		}
		return value;
		
	}

	/**
	 * 对象转数组
	 */
	protected static Object[] field2Values(String[] fields, Object o) throws Exception {
		Object[] values = new Object[fields.length];
		for (int i = 0; i < fields.length; i++) {
			values[i] = fieldNametoValues(fields[i], o);
		}
		return values;
	}

	/**
	 * 循环LIST对象拼接EASYUI格式的JSON数据
	 */
	private static String listtojson(String[] fields, int total, List<?> list, String[] footers) throws Exception {
		Object[] values = new Object[fields.length];
		StringBuilder jsonTemp = new StringBuilder();

		jsonTemp.append("{\'total\':").append(total).append(",\'rows\':[");
		for (int j = 0; j < list.size(); j++) {
			Object entity = list.get(j);

			jsonTemp.append("{\'state\':\'closed\',");
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i];
				if (entity instanceof Map) {
					values[i] = ((Map<?, ?>) entity).get(fieldName);
				} else {
					values[i] = fieldNametoValues(fieldName, entity);
				}
				jsonTemp.append("\'").append(fieldName).append("\'");
				jsonTemp.append(":\'").append(values[i]).append("\'");
				if (i != fields.length - 1) {
					jsonTemp = jsonTemp.append(",");
				}
			}
			if (j != list.size() - 1) {
				jsonTemp.append("},");
			} else {
				jsonTemp.append("}");
			}
		}
		jsonTemp.append("]");

		if (footers != null) {
			jsonTemp.append(",\'footer\':[{");
			jsonTemp.append("\'name\':\'合计\',");
			for (String footer : footers) {
				String[] tmps = footer.split(":");
				String footerFiled = tmps[0];
				Object value = null;
				if (tmps.length == 2) {
					value = tmps[1];
				} else {
					value = getTotalValue(footerFiled, list);
				}
				jsonTemp.append("\'").append(footerFiled).append("\'");
				jsonTemp.append(":\'").append(value).append("\',");
			}
			jsonTemp.delete(jsonTemp.length() - 1, jsonTemp.length());
			jsonTemp.append("}]");
		}
		jsonTemp.append("}");
		return jsonTemp.toString();
	}

	/**
	 * 计算指定列的合计
	 */
	private static Object getTotalValue(String fieldName, List<?> list) {
		Double sum = 0D;
		try {
			for (int j = 0; j < list.size(); j++) {
				Double v = 0d;
				String vstr = String.valueOf(fieldNametoValues(fieldName, list.get(j)));
				if (StringUtils.isNotEmpty(vstr)) {
					v = Double.valueOf(vstr);
				}
				sum += v;
			}
		} catch (Exception e) {
			return "";
		}
		return sum;
	}

	/**
	 * 返回列表JSONObject对象
	 */
	private static JSONObject getJson(DataGrid dg) {
		JSONObject jObject = null;
		try {
			if (StringUtils.isNotEmpty(dg.getFooter())) {
				jObject = JSONObject.parseObject(listtojson(dg.getField().split(","), dg.getTotal(), dg.getResults(), dg.getFooter().split(",")));
			} else {
				jObject = JSONObject.parseObject(listtojson(dg.getField().split(","), dg.getTotal(), dg.getResults(), null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jObject;
	}

	/**
	 * 返回easyui DataGrid格式的JSON数据
	 */
	public static void datagrid(HttpServletResponse response, DataGrid dg) {
		response.setCharacterEncoding("gb2312");
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		JSONObject object = TagUtil.getJson(dg);
		try {
			PrintWriter pw = response.getWriter();
			pw.write(object.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
