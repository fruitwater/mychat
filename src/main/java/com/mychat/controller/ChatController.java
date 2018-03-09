package com.mychat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.gson.Gson;
import com.mychat.entity.Chat;
import com.mychat.entity.User;
import com.mychat.form.ChatForm;
import com.mychat.form.UserForm;
import com.mychat.service.ChatService;

@Controller
@RequestMapping("/chat")
@SessionAttributes(value = "user")
public class ChatController {

	private Gson gson = new Gson();
	
	@ModelAttribute("user")
	private User user() {
		return new User("");
	}
	
	@Autowired
	private ChatService chatService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(@ModelAttribute("user") User user, Model model) {
		List<Chat> chats = chatService.getAllChats();
		model.addAttribute("chats",chats);
		model.addAttribute("user", user);
		model.addAttribute("login", !user.getName().equals(""));
		return "chat";
	}
	
	@RequestMapping(value = "/load",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String load() {
		List<Chat> chats = chatService.getAllChats();
		return gson.toJson(chats);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String write(@ModelAttribute("user") User user,@RequestBody@Valid ChatForm chatForm, Errors errors) throws Exception {

		if(errors.hasErrors()) {
			return makeErrorJson(errors);
		}
		Chat chat = new Chat(user, chatForm.getMessage());
		chatService.insert(chat);
		List<Chat> chats = chatService.getAllChats();
		return gson.toJson(chats);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("name") String name, RedirectAttributes redirectAttributes,
			@ModelAttribute("user") User user, @Validated UserForm userForm, BindingResult result,
			RedirectAttributes attributes, HttpServletRequest request, HttpServletResponse response) {

		user = new User(name);
		FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
		flashMap.put("errors", result.getAllErrors());
		RequestContextUtils.getFlashMapManager(request).saveOutputFlashMap(flashMap, request, response);
		return "redirect:/chat";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(@ModelAttribute("user") User user, Model model) {
		user.setName("");
		return "redirect:/chat";
	}

	
	private String makeErrorJson(Errors errors) {
		List<String> messages = new ArrayList<>();
		
		for(ObjectError error:errors.getAllErrors()) {
			messages.add(error.getDefaultMessage());
		}
		
		Map<String,Object> json = new HashMap<>();
		json.put("messages",messages);
		json.put("status", 400);
		Gson gson = new Gson();
		return gson.toJson(json);
	}
}
