package com.pfyuit.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfyuit.myblog.dao.UserDAO;
import com.pfyuit.myblog.domain.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public void save(User User) {
		userDAO.save(User);
	}

	public void delete(User User) {
		userDAO.delete(User);
	}

	public void update(User User) {
		userDAO.update(User);
	}

	public List<User> findAll() {
		return userDAO.findAll();
	}

}
