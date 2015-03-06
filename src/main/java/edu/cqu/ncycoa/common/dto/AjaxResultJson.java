package edu.cqu.ncycoa.common.dto;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class AjaxResultJson {
	private boolean success = true;// �Ƿ�ɹ�
	private String msg = "�����ɹ�";// ��ʾ��Ϣ
	private Object obj = null;// ������Ϣ
	private Map<String, Object> attributes;// ��������

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getJsonStr() {
		JSONObject obj = new JSONObject();
		obj.put("success", this.isSuccess());
		obj.put("msg", this.getMsg());
		obj.put("obj", this.obj);
		obj.put("attributes", this.attributes);
		return obj.toJSONString();
	}
}
