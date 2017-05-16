package edu.cqu.ncycoa.common.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mascloud.sdkclient.Client;

@org.springframework.stereotype.Service("WebServiceImpl")
@Transactional
public class WebServiceImpl {
    private static final String SERVICE_ENDPOINT = "http://218.200.169.244:10088/IMAS/services/smswebservice?wsdl";  
    private static final String servicecode = "106573190009"; 
    private static final String account = "ncycgs"; 
    private static final String password = "ncycgs"; 
    
	public static String SendMessage(String phone,String content) { 
		String res= "";
		try {
			final Client client =  Client.getInstance();
			// ��ʽ����IP����¼��֤URL���û��������룬���ſͻ�����
			client.login("http://mas.ecloud.10086.cn/app/sdk/login", "ncyc", "aA@12345678","�Ĵ�ʡ�̲ݹ�˾�ϳ��й�˾");
			// ���Ի���IP
			String[] phones = phone.split(","); 
			//client.login("http://112.33.1.13/app/sdk/login", "ncyc", "aA@12345678","�Ĵ�ʡ�̲ݹ�˾�ϳ��й�˾");
			int sendResult = client. sendDSMS (phones,
					content, "",  1,"dM9L3lWM", UUID.randomUUID().toString(),true);
			System.out.println("���ͽ��: " + sendResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
        return res;
    } 
	
	
}
