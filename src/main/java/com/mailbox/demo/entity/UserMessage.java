package com.mailbox.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_MESSAGE")
public class UserMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@ManyToOne
    @JoinColumn(name="RECEIVER_USER_ID")
    private User receiverUserId;

	@ManyToOne
    @JoinColumn(name="SENDER_USER_ID")
    private User senderUserId;
	
	@ManyToOne
    @JoinColumn(name="MESSAGE_ID")
    private Message messageId;

	public UserMessage() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getReceiverUserId() {
		return receiverUserId;
	}

	public void setReceiverUserId(User receiverUserId) {
		this.receiverUserId = receiverUserId;
	}

	public User getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(User senderUserId) {
		this.senderUserId = senderUserId;
	}

	public Message getMessageId() {
		return messageId;
	}

	public void setMessageId(Message messageId) {
		this.messageId = messageId;
	}

}
