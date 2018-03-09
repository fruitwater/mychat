package com.mychat.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mychat.entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Serializable>{

}
