package edu.cqu.gem.common.util.dao;

import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

public class QueryUtils {

	public static <T> TypedQueryBuilder<T> getTQBuilder(T searchObj, Map<String, String[]> parameterMap) {
		@SuppressWarnings("unchecked")
		TypedQueryBuilder<T> builder = new TypedQueryBuilder<T>((Class<T>) searchObj.getClass(), "e");
		doGetTQBuilder(builder, searchObj, parameterMap, "e");

		return builder;
	}

	private static <T> void doGetTQBuilder(TypedQueryBuilder<T> builder, Object searchObj, Map<String, String[]> parameterMap, String alias) {
		PropertyDescriptor properties[] = PropertyUtils.getPropertyDescriptors(searchObj);
		String aliasName, name, type;
		for (int i = 0; i < properties.length; i++) {

			name = properties[i].getName();
			type = properties[i].getPropertyType().toString();
			aliasName = alias + "." + name;

			try {
				if (judgedIsUselessField(name) || !PropertyUtils.isReadable(searchObj, name)) {
					continue;
				}
				// ��� �ж��Ƿ�������ֵ
				String beginValue = null;
				String endValue = null;
				if (parameterMap != null && parameterMap.containsKey(name + BEGIN)) {
					beginValue = parameterMap.get(name + BEGIN)[0].trim();
				}
				if (parameterMap != null && parameterMap.containsKey(name + END)) {
					endValue = parameterMap.get(name + END)[0].trim();
				}

				Object value = PropertyUtils.getSimpleProperty(searchObj, name);
				// �������ͷ��ദ��
				if ("class java.lang.String".equals(type)) {

					final String strValue = (String) value;
					if (StringUtils.isNotEmpty(strValue)) {
						// [1].In ���������ѯ{���Ÿ�������}
						if (strValue.indexOf(",") >= 0) {
							// ҳ�������ѯ�����������ȡ���ֶε�Ĭ��������
							if (strValue.indexOf(" ") >= 0) {
								final String val = strValue.substring(strValue.indexOf(" "));
								builder.addRestriction(aliasName, "=", val);
							} else {
								final String[] vs = strValue.split(",");
								builder.addRestriction(aliasName, "in", vs);
							}
						}
						// [2].ģ����ѯ{����* �ǺŵĲ���}
						else if (strValue.indexOf("*") >= 0) {
							builder.addRestriction(aliasName, "like", strValue.replace("*", "%"));
						}
						// [3].��ƥ���ѯ{���ڣ�̾��}
						// (1).��Ϊ���ַ���
						else if (strValue.equals("!")) {
							builder.addRestriction(aliasName, "is NOT NULL", null);
						}
						// (2).��ΪNULL
						else if (strValue.toUpperCase().equals("!NULL")) {
							builder.addRestriction(aliasName, "is NOT NULL", null);
						}
						// (3).������ƥ��
						else if (strValue.indexOf("!") >= 0) {
							builder.addRestriction(aliasName, "!=", strValue.replace("!", ""));
						}
						// [4].ȫƥ���ѯ{û��������ŵĲ���}
						else {
							builder.addRestriction(aliasName, "=", strValue);
						}
					}

				} else if (type.contains("class java.lang") || type.contains("class java.math")) {

					if (value != null) {
						builder.addRestriction(aliasName, "=", value);
					} else if (StringUtils.isNotEmpty(beginValue)) {
						builder.addRestriction(aliasName, ">=", beginValue);
					} else if (StringUtils.isNotEmpty(endValue)) {
						builder.addRestriction(aliasName, "<=", beginValue);
					}

				} else if ("class java.util.Date".equals(type)) {
					if (StringUtils.isNotEmpty(beginValue)) {
						if (beginValue.length() == 19) {
							builder.addRestriction(aliasName, ">=", DATE_TIME.parse(beginValue));
						} else if (beginValue.length() == 10) {
							builder.addRestriction(aliasName, ">=", DATE.parse(beginValue));
						}
					}
					if (StringUtils.isNotEmpty(endValue)) {
						if (endValue.length() == 19) {
							builder.addRestriction(aliasName, "<=", DATE_TIME.parse(endValue));
						} else if (endValue.length() == 10) {
							// ����"yyyy-MM-dd"��ʽ���ڣ���ʱ��Ĭ��Ϊ0���ʴ����" 23:23:59"��ʹ��time�������Է����ѯ����ʱ������
							builder.addRestriction(aliasName, "<=", DATE_TIME.parse(endValue + " 23:23:59"));
						}
					}
					if (value != null) {
						builder.addRestriction(aliasName, "=", value);
					}
				} else if (isJavaClass(properties[i].getPropertyType())) {
					if (value != null && itIsNotAllEmpty(value)) {
						// �����ʵ����,��������,����������ѯ����
						doGetTQBuilder(builder, value, parameterMap, aliasName);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static final String END = "_end";
	private static final String BEGIN = "_begin";

	private static boolean judgedIsUselessField(String name) {
		return "class".equals(name) || "ids".equals(name) || "page".equals(name) || "rows".equals(name) || "sort".equals(name)
				|| "order".equals(name);
	}

	private static boolean isJavaClass(Class<?> clazz) {
		boolean isBaseClass = false;
		if (clazz.isArray()) {
			isBaseClass = false;
		} else if (clazz.isPrimitive() || clazz.getPackage() == null || clazz.getPackage().getName().equals("java.lang")
				|| clazz.getPackage().getName().equals("java.math") || clazz.getPackage().getName().equals("java.util")) {
			isBaseClass = true;
		}
		return isBaseClass;
	}

	private static final SimpleDateFormat DATE_TIME = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static final SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");

	private static boolean itIsNotAllEmpty(Object param) {
		boolean isNotEmpty = false;
		try {
			PropertyDescriptor properties[] = PropertyUtils.getPropertyDescriptors(param);
			String name;
			for (int i = 0; i < properties.length; i++) {
				name = properties[i].getName();
				if ("class".equals(name) || !PropertyUtils.isReadable(param, name)) {
					continue;
				}
				if (Map.class.isAssignableFrom(properties[i].getPropertyType())) {
					Map<?, ?> map = (Map<?, ?>) PropertyUtils.getSimpleProperty(param, name);
					if (map != null && map.size() > 0) {
						isNotEmpty = true;
						break;
					}
				} else if (Collection.class.isAssignableFrom(properties[i].getPropertyType())) {
					Collection<?> c = (Collection<?>) PropertyUtils.getSimpleProperty(param, name);
					if (c != null && c.size() > 0) {
						isNotEmpty = true;
						break;
					}
				} else if (PropertyUtils.getSimpleProperty(param, name) != null) {
					isNotEmpty = true;
					break;
				}
			}
		} catch (Exception e) {

		}
		return isNotEmpty;
	}
}
