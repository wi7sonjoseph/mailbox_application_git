package com.mailbox.demo.pojo;

public class UserMessageTO {
	private Integer id;
	private Integer receiverUserId;
	private Integer senderUserId;
	private Integer messageId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReceiverUserId() {
		return receiverUserId;
	}
	public void setReceiverUserId(Integer receiverUserId) {
		this.receiverUserId = receiverUserId;
	}
	public Integer getSenderUserId() {
		return senderUserId;
	}
	public void setSenderUserId(Integer senderUserId) {
		this.senderUserId = senderUserId;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
}
