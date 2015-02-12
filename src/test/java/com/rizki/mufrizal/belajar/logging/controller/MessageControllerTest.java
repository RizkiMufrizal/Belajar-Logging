package com.rizki.mufrizal.belajar.logging.controller;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.rizki.mufrizal.belajar.logging.WebApplication;
import com.rizki.mufrizal.belajar.logging.domain.Message;
import com.rizki.mufrizal.belajar.logging.service.MessageService;
import com.rizki.mufrizal.belajar.logging.service.impl.MessageServiceImplTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { WebApplication.class })
@WebAppConfiguration
@IntegrationTest("server.port:0")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:Message.xml")
public class MessageControllerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		messageSave = new Message();
		messageUpdate = new Message();
		messageDelete = new Message();

		LOGGER.debug("SetUp Before Class");
	}

	@Autowired
	private MessageService messageService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageServiceImplTest.class);
	static Message messageSave;
	static Message messageUpdate;
	static Message messageDelete;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(
				this.webApplicationContext).build();
		LOGGER.info("Set Up Mock MVC");

		messageSave.setMessage("Hello");
		messageSave.setTujuan("rizki mufrizal");
		messageSave.setTanggalMasuk(new Date());

		messageUpdate.setMessage("Word");
		messageUpdate.setTujuan("rizki mufrizal");
		messageUpdate.setTanggalMasuk(new Date());

		messageDelete.setMessage("Hello");

		LOGGER.debug("SetUp Variabel");
	}

	@Test
	public void testDeleteMessages() throws Exception {
		messageService.save(messageSave);
		Message message = messageService.findByTanggalMasuk(new Date());

		messageDelete.setIdMessage(message.getIdMessage());

		mockMvc.perform(
				delete(
						MessageController.API + MessageController.MESSAGE + "/"
								+ messageDelete.getIdMessage()).contentType(
						MediaType.APPLICATION_JSON).accept(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasKey("success")))
				.andExpect(jsonPath("$", hasKey("info")))
				.andExpect(jsonPath("success", is(Boolean.TRUE)))
				.andExpect(jsonPath("info", is("Data Berhasil Di Hapus")));

		LOGGER.info("Delete Message");
	}

	@Test
	public void testGetMessages() throws Exception {
		mockMvc.perform(get(MessageController.API + MessageController.MESSAGE))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)))

				.andExpect(jsonPath("$[0].tujuan", is("rizki")))
				.andExpect(jsonPath("$[0].message", is("coba")))
				.andExpect(
						jsonPath("$[0].tanggalMasuk", is(new SimpleDateFormat(
								"yyyy-MM-dd").format(new SimpleDateFormat(
								"dd/MM/yyyy").parse("1/1/2012")))))
				.andExpect(jsonPath("$[0].idMessage", is("111")))

				.andExpect(jsonPath("$[1].tujuan", is("mufrizal")))
				.andExpect(jsonPath("$[1].message", is("dicoba")))
				.andExpect(
						jsonPath("$[1].tanggalMasuk", is(new SimpleDateFormat(
								"yyyy-MM-dd").format(new SimpleDateFormat(
								"dd/MM/yyyy").parse("1/1/2012")))))
				.andExpect(jsonPath("$[1].idMessage", is("222")));

		LOGGER.debug("Test Get List Message");
	}

	@Test
	public void testSaveMessages() throws Exception {
		mockMvc.perform(
				post(MessageController.API + MessageController.MESSAGE)
						.contentType(MediaType.APPLICATION_JSON)
						.content(
								new ObjectMapper()
										.writeValueAsString(messageSave))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$", hasKey("success")))
				.andExpect(jsonPath("$", hasKey("info")))
				.andExpect(jsonPath("success", is(Boolean.TRUE)))
				.andExpect(jsonPath("info", is("Data Berhasil Disimpan")));

		LOGGER.info("Save Message");
	}

	@Test
	public void testUpdateMessages() throws Exception {
		messageService.save(messageSave);
		Message message = messageService.findByTanggalMasuk(new Date());

		messageUpdate.setIdMessage(message.getIdMessage());

		mockMvc.perform(
				put(MessageController.API + MessageController.MESSAGE)
						.contentType(MediaType.APPLICATION_JSON)
						.content(
								new ObjectMapper()
										.writeValueAsString(messageUpdate))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasKey("success")))
				.andExpect(jsonPath("$", hasKey("info")))
				.andExpect(jsonPath("success", is(Boolean.TRUE)))
				.andExpect(jsonPath("info", is("Data Berhasil Di Edit")));

		LOGGER.info("Update Message");
	}

}
