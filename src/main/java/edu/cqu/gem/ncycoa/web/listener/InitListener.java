package edu.cqu.gem.ncycoa.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dao.system.Bm;
import com.db.ConnectionPool;

import edu.cqu.gem.ncycoa.service.SystemService;
import edu.cqu.gem.ncycoa.util.SystemUtils;

public class InitListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//==================================================================================================
		DataSource ds = WebApplicationContextUtils.getWebApplicationContext(
				sce.getServletContext()).getBean("dataSource", com.alibaba.druid.pool.DruidDataSource.class);
		ConnectionPool.setDataSource(ds);
		//==================================================================================================
		
		SystemService service = SystemUtils.getSystemService(sce.getServletContext());
		service.initAllDataDictionarys();// º”‘ÿ±‡¬Î
		
		Bm.Init();// º”‘ÿ±‡¬Î
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		return;
	}

}
