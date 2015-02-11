package com.rizki.mufrizal.belajar.logging.service;

import java.util.List;

import com.rizki.mufrizal.belajar.logging.domain.Message;

public interface MessageService {
	public void save(Message message);
	public void update(Message message);
	public void delete(Message message);
	public Message getMessage(String idMessage);
	public List<Message> getMessages();
}
