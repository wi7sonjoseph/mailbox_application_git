package com.mailbox.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mailbox.demo.pojo.MessageTO;
import com.mailbox.demo.service.MessageService;
@RestController
@RequestMapping("/")
public class MessageController {

	@Autowired
	private MessageService messageService;
			
	@PostMapping("/message")
	public ResponseEntity<String> saveMessage(@RequestBody MessageTO messageTO) {
		try {
			if (messageTO != null) {
				messageService.saveMessage(messageTO);
			}else {
				return new ResponseEntity<>("Message not found", HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity<>("Exception:"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Sent Successful!", HttpStatus.OK);
	}	
	
	@PutMapping("/message")
	public ResponseEntity<String> updateMessage(@RequestBody MessageTO messageTO) {
		try {
			if (messageTO != null) {
				messageService.updateMessage(messageTO);
			}else {
				return new ResponseEntity<>("Message not found", HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity<>("Exception:"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Sent Successful!", HttpStatus.OK);	
	}
}
