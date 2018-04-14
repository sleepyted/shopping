package service;

import bean.Good;
import dao.GoodDao;
import dao.PubDefine;

import java.util.List;

/**
 */
public class GoodService {

	GoodDao goodDao = new GoodDao();
	public int addGood(Good good){
		return  goodDao.insert(good);
	}
	public Good findGood(int id){
		return goodDao.findById(id);
	}
	public List<Good> findAllGood(){
		return goodDao.findAllGood();
	}
	public void delGood(int id){
		goodDao.del(id);
	}
	public void updateGood(Good good){
		goodDao.update(good);
	}
}
