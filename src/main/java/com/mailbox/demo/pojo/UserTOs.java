package com.mailbox.demo.pojo;

import java.util.List;

public class UserTOs {
	private Integer count;
	private List<UserTO> lstUserTO;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<UserTO> getLstUserTO() {
		return lstUserTO;
	}
	public void setLstUserTO(List<UserTO> lstUserTO) {
		this.lstUserTO = lstUserTO;
	}
}
