package com.mailbox.demo.pojo;

import java.util.List;

public class UserTO {
	private Integer id;
	private String email;
	private String firstName;
	private String lastName;
	private String userName;
	
	private List<MessageTO> inboxMessages;
	private List<MessageTO> sentMessages;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<MessageTO> getInboxMessages() {
		return inboxMessages;
	}
	public void setInboxMessages(List<MessageTO> inboxMessages) {
		this.inboxMessages = inboxMessages;
	}
	public List<MessageTO> getSentMessages() {
		return sentMessages;
	}
	public void setSentMessages(List<MessageTO> sentMessages) {
		this.sentMessages = sentMessages;
	}
	
}
