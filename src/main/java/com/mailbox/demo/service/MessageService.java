package com.mailbox.demo.service;

import com.mailbox.demo.pojo.MessageTO;

public interface MessageService {

	public void saveMessage(MessageTO messageTO);
	public void updateMessage(MessageTO messageTO);

}
