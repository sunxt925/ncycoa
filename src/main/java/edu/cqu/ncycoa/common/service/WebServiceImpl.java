package edu.cqu.ncycoa.common.service;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Service("WebServiceImpl")
@Transactional
public class WebServiceImpl {
    private static final String SERVICE_ENDPOINT = "http://218.200.169.244:10088/IMAS/services/smswebservice?wsdl";  
    private static final String servicecode = "106573190009"; 
    private static final String account = "ncycgs"; 
    private static final String password = "ncycgs"; 
    
	public static String SendMessage(String phone,String content) {  
        try {  
            Service service = new Service();  
            Call call = (Call) service.createCall();  
            call.setTargetEndpointAddress(new java.net.URL(  
                    SERVICE_ENDPOINT));  
            //下面名字查询的http://127.0.0.1:8080/WebServiceTest/services/HelloServices?wsdl文件里有 
            QName qn=new QName("http://webservice.service.imas.vicare.com",  
                    "sendSms");
            call.setOperationName(qn);  
            call.setReturnType(org.apache.axis.Constants.XSD_STRING);  
            call.addParameter("servicecode", org.apache.axis.Constants.XSD_STRING,  
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter("account", org.apache.axis.Constants.XSD_STRING,  
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter("password", org.apache.axis.Constants.XSD_STRING,  
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter("phone", org.apache.axis.Constants.XSD_STRING,  
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter("content", org.apache.axis.Constants.XSD_STRING,  
                    javax.xml.rpc.ParameterMode.IN);
            try {  
                //远程调用发布的方法  
                String ret =  (String) call.invoke(new Object[] {servicecode,account,password,phone,content});   
                return ret;  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (ServiceException e) {  
            e.printStackTrace();  
        }
		return null;  
    } 
	public String deliver(String taskId,String phone) {  
        try {  
            Service service = new Service();  
            Call call = (Call) service.createCall();  
            call.setTargetEndpointAddress(new java.net.URL(  
                    SERVICE_ENDPOINT));  
            //下面名字查询的http://127.0.0.1:8080/WebServiceTest/services/HelloServices?wsdl文件里有 
            QName qn=new QName("http://webservice.service.imas.vicare.com",  
                    "deliver");
            call.setOperationName(qn);  
            call.setReturnType(org.apache.axis.Constants.XSD_STRING);  
            call.addParameter("servicecode", org.apache.axis.Constants.XSD_STRING,  
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter("taskId", org.apache.axis.Constants.XSD_STRING,  
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter("phone", org.apache.axis.Constants.XSD_STRING,  
                    javax.xml.rpc.ParameterMode.IN);
            try {  
                //远程调用发布的方法  
                String ret =  (String) call.invoke(new Object[] {servicecode,taskId,phone});   
                return ret;  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (ServiceException e) {  
            e.printStackTrace();  
        }
		return null;  
    } 
	public String receiveMsg() {  
        try {  
            Service service = new Service();  
            Call call = (Call) service.createCall();  
            call.setTargetEndpointAddress(new java.net.URL(  
                    SERVICE_ENDPOINT));  
            //下面名字查询的http://127.0.0.1:8080/WebServiceTest/services/HelloServices?wsdl文件里有 
            QName qn=new QName("http://webservice.service.imas.vicare.com",  
                    "receiveMsg");
            call.setOperationName(qn);  
            call.setReturnType(org.apache.axis.Constants.XSD_STRING);  
            call.addParameter("servicecode", org.apache.axis.Constants.XSD_STRING,  
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter("account", org.apache.axis.Constants.XSD_STRING,  
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter("password", org.apache.axis.Constants.XSD_STRING,  
                    javax.xml.rpc.ParameterMode.IN);
            try {  
                //远程调用发布的方法  
                String ret =  (String) call.invoke(new Object[] {servicecode,account,password});   
                return ret;  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (ServiceException e) {  
            e.printStackTrace();  
        }
		return null;  
    } 
//	public static void main(String[] args){
//		SendMessage("15086909277","machao");
//	}
}
