package com.mychat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mychat.entity.Chat;
import com.mychat.repository.ChatRepository;
@Service
@Transactional
@ComponentScan
public class ChatServiceImpl implements ChatService{

	@Autowired
	ChatRepository chatRepository;
	
	
	@Override
	public List<Chat> getAllChats() {
		return chatRepository.findAll();
	}

	@Override
	public void insert(Chat chat) {
		chatRepository.save(chat);
	}
	
	

}
