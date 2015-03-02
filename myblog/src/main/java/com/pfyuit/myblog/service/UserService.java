package com.pfyuit.myblog.service;

import java.util.List;

import com.pfyuit.myblog.domain.User;

public interface UserService {

	public abstract void save(User User);

	public abstract void delete(User User);

	public abstract void update(User User);

	public abstract List<User> findAll();

}