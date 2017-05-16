package edu.cqu.ncycoa.common.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.system.UserInfo;

import edu.cqu.ncycoa.dao.CommonDao;
import edu.cqu.ncycoa.domain.Log;
import edu.cqu.ncycoa.util.BrowserUtils;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Service("meetingService")
@Transactional
public class MeetingServiceImpl extends CommonServiceImpl implements MeetingService{
	@Resource(name="commonDao")
	private CommonDao commonDao;
	@Override
	public void addLog(String logContent, Short loglevel, Short operatetype) {
		HttpServletRequest request = SystemUtils.getRequest();
		String broswer = BrowserUtils.checkBrowse(request);
		Log log = new Log();
		log.setLogContent(logContent);
		log.setLogLevel(loglevel);
		log.setOperateType(operatetype);
		log.setNote(ConvertUtils.getIpAddrByRequest(request));
		log.setBroswer(broswer);
		log.setOperateTime(new Date());
		UserInfo user = SystemUtils.getSessionUser();
		log.setOperatorName(user.getUsername());
		log.setOperatorName(user.getStaffcode());
		
		commonDao.saveEntity(log);
	}
}
