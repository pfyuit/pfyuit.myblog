package com.pfyuit.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfyuit.myblog.dao.UserDao;
import com.pfyuit.myblog.domain.User;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDAO;

	@Override
	public void save(User User) {
		userDAO.save(User);
	}

	@Override
	public void delete(User User) {
		userDAO.delete(User);
	}

	@Override
	public void update(User User) {
		userDAO.update(User);
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

}
