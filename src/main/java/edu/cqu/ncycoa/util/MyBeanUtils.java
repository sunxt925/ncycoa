package edu.cqu.ncycoa.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

public class MyBeanUtils extends org.apache.commons.beanutils.BeanUtils {

	private final static Logger logger = Logger.getLogger(MyBeanUtils.class);
	
	private static void convert(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException {
		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}
		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}

		// Copy the properties, converting as necessary
		if (orig instanceof DynaBean) {
			DynaProperty origDescriptors[] = ((DynaBean) orig).getDynaClass().getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if (PropertyUtils.isWriteable(dest, name)) {
					Object value = ((DynaBean) orig).get(name);
					try {
						copyProperty(dest, name, value);
					} catch (Exception e) {
						; // Should not happen
					}

				}
			}
		} else if (orig instanceof Map) {
			Iterator names = ((Map) orig).keySet().iterator();
			while (names.hasNext()) {
				String name = (String) names.next();
				if (PropertyUtils.isWriteable(dest, name)) {
					Object value = ((Map) orig).get(name);
					try {
						copyProperty(dest, name, value);
					} catch (Exception e) {
						; // Should not happen
					}

				}
			}
		} else
		/* if (orig is a standard JavaBean) */
		{
			PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(orig);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				// String type =
				// origDescriptors[i].getPropertyType().toString();
				if ("class".equals(name)) {
					continue; // No point in trying to set an object's class
				}
				if (PropertyUtils.isReadable(orig, name) && PropertyUtils.isWriteable(dest, name)) {
					try {
						Object value = PropertyUtils.getSimpleProperty(orig, name);
						copyProperty(dest, name, value);
					} catch (java.lang.IllegalArgumentException ie) {
						; // Should not happen
					} catch (Exception e) {
						; // Should not happen
					}

				}
			}
		}

	}

	/**
	 * ���󿽱� Դ�����п�ֵ��������Ŀ�����
	 */
	public static void copyBeanNotNull2Bean(Object source, Object dest) {
		PropertyDescriptor properties[] = PropertyUtils.getPropertyDescriptors(source);
		for (int i = 0; i < properties.length; i++) {
			String name = properties[i].getName();
			if ("class".equals(name)) {
				continue;
			}
			if (PropertyUtils.isReadable(source, name) && PropertyUtils.isWriteable(dest, name)) {
				try {
					Object value = PropertyUtils.getSimpleProperty(source, name);
					if (value != null) {
						copyProperty(dest, name, value);
					}
				} catch (Exception e) {
					logger.error("���Ը���ʱ����, Դ�� " + source.getClass().getName() + " ,Ŀ���� " + dest.getClass().getName(), e);
				}
			}
		}
	}

	/**
	 * ��orig��dest��ͬ���Ե�value���Ƶ�dest��
	 */
	public static void copyBean2Bean(Object dest, Object orig) throws Exception {
		convert(dest, orig);
	}

	public static void copyBean2Map(Map<String, Object> map, Object bean) {
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(bean);
		for (int i = 0; i < pds.length; i++) {
			PropertyDescriptor pd = pds[i];
			String propname = pd.getName();
			try {
				Object propvalue = PropertyUtils.getSimpleProperty(bean, propname);
				map.put(propname, propvalue);
			} catch (IllegalAccessException e) {
				// e.printStackTrace();
			} catch (InvocationTargetException e) {
				// e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// e.printStackTrace();
			}
		}
	}

	/**
	 * ��Map�ڵ�key��Bean��������ͬ�����ݸ��Ƶ�BEAN��
	 * 
	 * @param bean
	 *            Object
	 * @param properties
	 *            Map
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyMap2Bean(Object bean, Map properties) throws IllegalAccessException, InvocationTargetException {
		// Do nothing unless both arguments have been specified
		if ((bean == null) || (properties == null)) {
			return;
		}
		// Loop through the property name/value pairs to be set
		Iterator<?> names = properties.keySet().iterator();
		while (names.hasNext()) {
			String name = (String) names.next();
			// Identify the property name and value(s) to be assigned
			if (name == null) {
				continue;
			}
			Object value = properties.get(name);
			try {
				Class<?> clazz = PropertyUtils.getPropertyType(bean, name);
				if (null == clazz) {
					continue;
				}
				String className = clazz.getName();
				if (className.equalsIgnoreCase("java.sql.Timestamp")) {
					if (value == null || value.equals("")) {
						continue;
					}
				}
				setProperty(bean, name, value);
			} catch (NoSuchMethodException e) {
				continue;
			}
		}
	}

	/**
	 * �Զ�תMap keyֵ��д ��Map�ڵ�key��Bean��������ͬ�����ݸ��Ƶ�BEAN��
	 * 
	 * @param bean
	 *            Object
	 * @param properties
	 *            Map
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyMap2Bean_Nobig(Object bean, Map properties) throws IllegalAccessException, InvocationTargetException {
		// Do nothing unless both arguments have been specified
		if ((bean == null) || (properties == null)) {
			return;
		}
		// Loop through the property name/value pairs to be set
		Iterator names = properties.keySet().iterator();
		while (names.hasNext()) {
			String name = (String) names.next();
			// Identify the property name and value(s) to be assigned
			if (name == null) {
				continue;
			}
			Object value = properties.get(name);
			// ����Ӧ�ô�СдӦ������(����ȡ�������������)
			// name = name.toLowerCase();
			try {
				if (value == null) { // ����Date���ͣ��ö�������nullʱ�����
					continue; // ���Ϊnull������ (��������������ʼֵҲ���Ա�����)
				}
				Class clazz = PropertyUtils.getPropertyType(bean, name);
				if (null == clazz) { // ��bean��������Բ�����
					continue;
				}
				String className = clazz.getName();
				// ��ʱ�Բߣ����������Ĭ�ϵ�����ת��ʱ�����
				if (className.equalsIgnoreCase("java.util.Date")) {
					value = new java.util.Date(((java.sql.Timestamp) value).getTime());// wait
																						// to
																						// do��ò����ʱ������,
																						// ����һ��ȷ��
				}
				// if (className.equalsIgnoreCase("java.sql.Timestamp")) {
				// if (value == null || value.equals("")) {
				// continue;
				// }
				// }
				setProperty(bean, name, value);
			} catch (NoSuchMethodException e) {
				continue;
			}
		}
	}

	/**
	 * Map�ڵ�key��Bean��������ͬ�����ݸ��Ƶ�BEAN�� ���ڴ��ڿ�ֵ��ȡĬ��ֵ
	 * 
	 * @param bean
	 *            Object
	 * @param properties
	 *            Map
	 * @param defaultValue
	 *            String
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyMap2Bean(Object bean, Map properties, String defaultValue) throws IllegalAccessException,
			InvocationTargetException {
		// Do nothing unless both arguments have been specified
		if ((bean == null) || (properties == null)) {
			return;
		}
		// Loop through the property name/value pairs to be set
		Iterator names = properties.keySet().iterator();
		while (names.hasNext()) {
			String name = (String) names.next();
			// Identify the property name and value(s) to be assigned
			if (name == null) {
				continue;
			}
			Object value = properties.get(name);
			try {
				Class clazz = PropertyUtils.getPropertyType(bean, name);
				if (null == clazz) {
					continue;
				}
				String className = clazz.getName();
				if (className.equalsIgnoreCase("java.sql.Timestamp")) {
					if (value == null || value.equals("")) {
						continue;
					}
				}
				if (className.equalsIgnoreCase("java.lang.String")) {
					if (value == null) {
						value = defaultValue;
					}
				}
				setProperty(bean, name, value);
			} catch (NoSuchMethodException e) {
				continue;
			}
		}
	}

	private MyBeanUtils() {
		super();
	}
}
