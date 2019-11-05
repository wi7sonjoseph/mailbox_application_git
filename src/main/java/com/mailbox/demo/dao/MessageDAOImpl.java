package com.mailbox.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mailbox.demo.entity.Message;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public void saveMessage(Message theMessage) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theMessage);
	}

	@Override
	public Message getMessageById(int messageId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Message message = currentSession.get(Message.class, messageId);
		return message;
	}
}











