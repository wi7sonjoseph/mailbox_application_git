package com.mailbox.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mailbox.demo.entity.Message;
import com.mailbox.demo.entity.User;
import com.mailbox.demo.entity.UserMessage;
import com.mailbox.demo.pojo.MessageTO;
import com.mailbox.demo.pojo.UserTO;
import com.mailbox.demo.pojo.UserTOs;
import com.mailbox.demo.service.UserService;
@RestController
@RequestMapping("/")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	// add mapping for GET /customers
	@GetMapping("/user")
	public UserTOs getUsers() {
		UserTOs userTOs = new UserTOs();
		List<UserTO> lstUserTO = new ArrayList<UserTO>();
		
		Integer count = 0;
		List<User> lstUsers = userService.getUsers();
		for (User user: lstUsers) {
			UserTO userTO = new UserTO();
			userTO.setId(user.getId());
			userTO.setUserName(user.getUserName());
			userTO.setFirstName(user.getFirstName());
			userTO.setLastName(user.getLastName());
			userTO.setEmail(user.getEmail());
			
			lstUserTO.add(userTO);
			count++;
		}
		userTOs.setLstUserTO(lstUserTO);
		userTOs.setCount(count);
		return userTOs;
	}
	
	@GetMapping("/user/{userId}")
	public UserTO getUserById(@PathVariable int userId, @RequestParam String screenFlag) {
		UserTO userTO = new UserTO();
		User user = userService.getUserById(userId);
		if (user != null) {
			List<UserMessage> lstUserMessages = new ArrayList<UserMessage>();
			if (screenFlag != null && "S".equalsIgnoreCase(screenFlag)) {
				lstUserMessages = user.getSentUserMessages();
			}else {
				lstUserMessages = user.getInboxUserMessages();
			}
			
			List<MessageTO> lstMessageTO = new ArrayList<MessageTO>();
			if (lstUserMessages != null && !lstUserMessages.isEmpty()) {
				for (UserMessage userMessage: lstUserMessages) {
					MessageTO messageTO = new MessageTO();
					
					Message message = userMessage.getMessageId();
					if (message != null) {
						messageTO.setId(message.getId());
						if (message.getMailBody() != null) {
							messageTO.setMailBody(message.getMailBody());
						}
						if (message.getReadStatus() != null) {
							messageTO.setReadStatus(message.getReadStatus().toString());
						}
						if (message.getSubject() != null) {
							messageTO.setSubject(message.getSubject());
						}
						
						User fromUser = userMessage.getSenderUserId();
						String fromName = fromUser.getFirstName()+" "+fromUser.getLastName();
						messageTO.setFromName(fromName);
						
						User toUser = userMessage.getReceiverUserId();
						String toName = toUser.getFirstName()+" "+toUser.getLastName();
						messageTO.setToName(toName);
					}
					
					lstMessageTO.add(messageTO);
				}
			}
			if (screenFlag != null && "S".equalsIgnoreCase(screenFlag)) {
				userTO.setSentMessages(lstMessageTO);
			}else {
				userTO.setInboxMessages(lstMessageTO);
			}
		}
		return userTO;
	}
}
