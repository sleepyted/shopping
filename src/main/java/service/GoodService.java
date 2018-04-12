package service;

import bean.Good;
import dao.GoodDao;
import dao.PubDefine;

/**
 */
public class GoodService {

	GoodDao goodDao = new GoodDao();
	public int addGood(Good good){
		return  goodDao.insert(good);
	}
}
