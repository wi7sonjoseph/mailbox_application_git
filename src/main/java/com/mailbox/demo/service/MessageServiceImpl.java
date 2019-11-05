package com.mailbox.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mailbox.demo.dao.MessageDAO;
import com.mailbox.demo.entity.Message;
import com.mailbox.demo.entity.User;
import com.mailbox.demo.entity.UserMessage;
import com.mailbox.demo.pojo.MessageTO;
import com.mailbox.demo.pojo.UserMessageTO;
import com.mailbox.demo.utility.CommonEnum;

@Service
public class MessageServiceImpl implements MessageService{


	@Autowired
	private MessageDAO messageDAO;
	

	@Override
	@Transactional
	public void saveMessage(MessageTO messageTO) {
		Message message = new Message();
		if (messageTO.getSubject() != null) {
			message.setSubject(messageTO.getSubject());
		}
		if (messageTO.getMailBody() != null) {
			System.out.println("Mail Body:"+messageTO.getMailBody());
			message.setMailBody(messageTO.getMailBody());
		}
		message.setReadStatus(CommonEnum.N);
		if (messageTO.getLstUserMessageTO() != null && !messageTO.getLstUserMessageTO().isEmpty()) {
			List<UserMessageTO> lstUserMessageTO = messageTO.getLstUserMessageTO();
			List<UserMessage> lstUserMessage = new ArrayList<UserMessage>();
			for (UserMessageTO userMessageTO : lstUserMessageTO) {
				UserMessage userMessage = new UserMessage();
				userMessage.setMessageId(message);
				userMessage.setSenderUserId(new User(userMessageTO.getSenderUserId()));
				userMessage.setReceiverUserId(new User(userMessageTO.getReceiverUserId()));
				
				lstUserMessage.add(userMessage);
			}
			if (lstUserMessage != null && !lstUserMessage.isEmpty()) {
				message.setUserMessages(lstUserMessage);
			}
		}
		
		messageDAO.saveMessage(message);
	}
	
	@Override
	@Transactional
	public void updateMessage(MessageTO messageTO) {	
		if (messageTO != null) {
			Message message = messageDAO.getMessageById(messageTO.getId());
			if (message != null) {
				/*Message cloneMessage = new Message();
				cloneMessage.setId(message.getId());
				cloneMessage.setSubject(message.getSubject());
				cloneMessage.setMailBody(message.getMailBody());
				cloneMessage.setUserMessages(message.getUserMessages());*/
				message.setReadStatus(CommonEnum.Y);
				messageDAO.saveMessage(message);
			}
		}
	}
	
}
