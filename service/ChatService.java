package com.mychat.service;

import java.util.List;

import com.mychat.entity.Chat;

public interface ChatService {
	
	List<Chat> getAllChats();
	
	void insert(Chat chat);

}
