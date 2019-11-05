package com.mailbox.demo.dao;

import java.util.List;

import com.mailbox.demo.entity.User;

public interface UserDAO {

	public List<User> getUsers();
	public User getUserById(int userId);
}
