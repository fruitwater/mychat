package com.mychat.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.annotation.CreatedDate;



@Entity
@Table(name = "chat")
@Configurable
public class Chat implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Chat() {
		
	}
	
	public Chat(User user,String chat) {
		this.user = user;
		this.chat = chat;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Column(name = "user")
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "name")
	@Size(max = 512,min = 1)
	private String chat;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

	@CreatedDate 
    @Column(name="created")
    private Date created;

	public String getCreated() {
		return created.toString();
	}
	
	@PrePersist
	public void onPrePersist() {
        setCreated(new Date());
   
    }

	public void setCreated(Date created) {
		this.created = created;
	}
}
