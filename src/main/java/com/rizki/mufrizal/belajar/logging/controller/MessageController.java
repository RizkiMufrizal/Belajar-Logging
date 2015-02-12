package com.rizki.mufrizal.belajar.logging.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rizki.mufrizal.belajar.logging.domain.Message;
import com.rizki.mufrizal.belajar.logging.service.MessageService;

@RestController
@RequestMapping(value = MessageController.API)
public class MessageController {

	public static final String API = "/api";
	public static final String MESSAGE = "/message";
	public static final String MESSAGE_DELETE = "/message/{idMessage}";

	@Autowired
	private MessageService messageService;

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = MESSAGE_DELETE, method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> deleteMessages(
	        @PathVariable("idMessage") String idMessage) {

		Message message = messageService.getMessage(idMessage);
		messageService.delete(message);

		Map<String, Object> hasilMap = new HashMap<String, Object>();
		hasilMap.put("success", Boolean.TRUE);
		hasilMap.put("info", "Data Berhasil Di Hapus");

		return hasilMap;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = MESSAGE, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Message> getMessages() {
		return messageService.getMessages();
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = MESSAGE, method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> saveMessages(@RequestBody Message message) {

		messageService.save(message);

		Map<String, Object> hasilMap = new HashMap<String, Object>();
		hasilMap.put("success", Boolean.TRUE);
		hasilMap.put("info", "Data Berhasil Disimpan");

		return hasilMap;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = MESSAGE, method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> updateMessages(@RequestBody Message message) {

		messageService.update(message);

		Map<String, Object> hasilMap = new HashMap<String, Object>();
		hasilMap.put("success", Boolean.TRUE);
		hasilMap.put("info", "Data Berhasil Di Edit");

		return hasilMap;
	}

}
