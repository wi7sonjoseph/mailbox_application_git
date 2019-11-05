package com.mailbox.demo.service;

import java.util.List;

import com.mailbox.demo.entity.User;

public interface UserService {

	public List<User> getUsers();
	public User getUserById(int userId);
}
