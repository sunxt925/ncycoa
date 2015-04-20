package edu.cqu.ncycoa.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.ncycoa.dao.CommonDao;

@Service("meetingService")
@Transactional
public class MeetingServiceImpl extends CommonServiceImpl implements MeetingService{
	@Resource(name="commonDao")
	private CommonDao commonDao;
}
