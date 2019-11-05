package com.mailbox.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mailbox.demo.dao.UserDAO;
import com.mailbox.demo.entity.User;

@Service
public class UserServiceImpl implements UserService{

	//need to inject customer dao
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}
	
	@Override
	@Transactional
	public User getUserById(int userId) {
		return userDAO.getUserById(userId);
	}
}
