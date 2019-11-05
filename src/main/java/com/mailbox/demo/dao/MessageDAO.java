package com.mailbox.demo.dao;

import com.mailbox.demo.entity.Message;

public interface MessageDAO {
	void saveMessage(Message theMessage);
	public Message getMessageById(int messageId);
}
