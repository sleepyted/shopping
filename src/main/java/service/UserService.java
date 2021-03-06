package service;

import bean.User;
import common.Util;
import dao.PubDefine;
import dao.UserDao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 */
public class UserService implements PubDefine{
	UserDao userDao = new UserDao();
	public int userRegister(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		user.setUserType("0");
		if(userDao.getUserByEmail(user.getEmail())){
			return STATUS_ALLREADY_FOUND;
		}
		user.setPassword(Util.EncoderByMd5(user.getPassword()));
		return userDao.insert(user);
	}

	public User userLogin(String email, String password) {
		User user = userDao.getUserByEmailPassword(email, password);
		if (user.getEmail() == null) return null;
		return user;
	}

	public User adminLogin(String email, String password){
		User user = userDao.getUserByEmailPassword(email, password);
		if(user.getEmail() == null || user.getUserType().equals("0")) {
			return null;
		}
		return user;
	}

	public void updateUser(User user) {
		userDao.update(user);
	}

	public List<User> findAllUser() {
		return userDao.getAll();
	}

	public void delUser(int id) {
		userDao.delUser(id);
	}
}
