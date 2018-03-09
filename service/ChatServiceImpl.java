package com.mychat.service;

import java.util.List;

import com.mychat.entity.Chat;
import com.repository.ChatRepository;
public class ChatServiceImpl implements ChatService{

	private ChatRepository chatRepository = new ChatRepository();
	
	@Override
	public List<Chat> getAllChats() {
		return chatRepository.findAll();
	}

	@Override
	public void insert(Chat chat) {
		chatRepository.insert(chat);
	}
	
	

}
