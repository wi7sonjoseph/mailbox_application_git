package com.mailbox.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mailbox.demo.utility.CommonEnum;

@Entity
@Table(name = "MESSAGE")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "MAIL_BODY")
	private String mailBody;
	
	@Enumerated(EnumType.STRING)
	@Column(name="READ_STATUS", columnDefinition="enum('N','Y')", nullable=false, length = 1)
	private CommonEnum readStatus;

	@Column(name = "SUBJECT")
	private String subject;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "messageId", fetch = FetchType.LAZY)
    private List<UserMessage> userMessages;
	
	public Message() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public CommonEnum getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(CommonEnum readStatus) {
		this.readStatus = readStatus;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<UserMessage> getUserMessages() {
		return userMessages;
	}

	public void setUserMessages(List<UserMessage> userMessages) {
		this.userMessages = userMessages;
	}
}
