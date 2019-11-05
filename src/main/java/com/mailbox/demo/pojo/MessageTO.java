package com.mailbox.demo.pojo;

import java.util.List;

public class MessageTO {
	private Integer id;
	private String mailBody;
	private String readStatus;
	private String subject;
	private Integer senderUserId;
	private String fromName;
	private String toName;
	
	private List<UserMessageTO> lstUserMessageTO;
	
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
	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Integer getSenderUserId() {
		return senderUserId;
	}
	public void setSenderUserId(Integer senderUserId) {
		this.senderUserId = senderUserId;
	}
	public List<UserMessageTO> getLstUserMessageTO() {
		return lstUserMessageTO;
	}
	public void setLstUserMessageTO(List<UserMessageTO> lstUserMessageTO) {
		this.lstUserMessageTO = lstUserMessageTO;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
}
