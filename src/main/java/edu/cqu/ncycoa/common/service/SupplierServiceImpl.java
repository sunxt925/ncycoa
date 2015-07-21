package edu.cqu.ncycoa.common.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.ncycoa.dao.SupplierDao;

@Service("supplierService")
@Transactional
public class SupplierServiceImpl  extends SystemServiceImpl implements SupplierService {

	public static String getGoodsType(){
		return SupplierDao.getGoodsType();
	}
	
	public static String getDepart(){
		return SupplierDao.getDepart();
	}
	
	public static String getEvaluContent(String year){
		return SupplierDao.getEvaluContent(year);
	}
	
	public static String getSupplier(){
		return SupplierDao.getSupplier();
	}
	
	public static int getContentCount(){
		return SupplierDao.getContentCount();
	}
	
	public static void removeByName(String name){
		SupplierDao.removeByName(name);
	}
	
	public static String getAllSupplier(){
		return SupplierDao.getAllSupplier();
	}
	
	public static boolean isEvalued(String depart, String supplier, String year){
		return SupplierDao.isEvalued(depart, supplier, year);
	}

	public static void startEvalu() {
		SupplierDao.startEvalu();
		
	}

	public static void closeEvalu() {
		SupplierDao.closeEvalu();
		
	}

	public static String getEvaluedContent(String year,String supplier) {
		return SupplierDao.getEvaluedContent(year,supplier);
	}
}
