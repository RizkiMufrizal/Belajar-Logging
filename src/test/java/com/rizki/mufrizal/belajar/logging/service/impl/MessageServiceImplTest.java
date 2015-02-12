package com.rizki.mufrizal.belajar.logging.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.rizki.mufrizal.belajar.logging.WebApplication;
import com.rizki.mufrizal.belajar.logging.domain.Message;
import com.rizki.mufrizal.belajar.logging.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { WebApplication.class })
@WebAppConfiguration
@IntegrationTest("server.port:0")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:Message.xml")
public class MessageServiceImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		messageSave = new Message();
		messageUpdate = new Message();
		messageDelete = new Message();

		LOGGER.debug("SetUp Before Class");
	}

	@Autowired
	private MessageService messageService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageServiceImplTest.class);
	static Message messageSave;
	static Message messageUpdate;

	static Message messageDelete;

	@Before
	public void setUp() throws Exception {
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
	public void testDelete() {
		messageService.save(messageSave);

		assertTrue(messageService.getMessages().size() > 2);
		assertTrue(messageService.getMessages().size() == 3);

		Message message = messageService.findByTanggalMasuk(new Date());

		messageDelete.setIdMessage(message.getIdMessage());

		messageService.delete(messageDelete);

		assertTrue(messageService.getMessages().size() > 1);
		assertTrue(messageService.getMessages().size() == 2);

		assertNull(messageService.getMessage(message.getIdMessage()));

		LOGGER.debug("Delete Data");
	}

	@Test
	public void testGetMessage() {

		assertNotNull(messageService.getMessage("111"));
		assertEquals("rizki", messageService.getMessage("111").getTujuan());

		LOGGER.debug("get Message");
	}

	@Test
	public void testGetMessages() {
		assertTrue(messageService.getMessages().size() > 1);
		assertTrue(messageService.getMessages().size() == 2);

		assertEquals("rizki", messageService.getMessages().get(0).getTujuan());

		LOGGER.debug("get All Message");
	}

	@Test
	public void testSave() {
		messageService.save(messageSave);

		assertTrue(messageService.getMessages().size() > 2);
		assertTrue(messageService.getMessages().size() == 3);

		assertEquals("rizki mufrizal",
				messageService.findByTanggalMasuk(new Date()).getTujuan());

		LOGGER.debug("Save Data");
	}

	@Test
	public void testUpdate() {
		messageService.save(messageSave);

		assertTrue(messageService.getMessages().size() > 2);
		assertTrue(messageService.getMessages().size() == 3);

		Message message = messageService.findByTanggalMasuk(new Date());

		messageUpdate.setIdMessage(message.getIdMessage());

		messageService.update(messageUpdate);

		assertTrue(messageService.getMessages().size() > 2);
		assertTrue(messageService.getMessages().size() == 3);

		assertEquals("Word", messageService.findByTanggalMasuk(new Date())
				.getMessage());

		LOGGER.debug("Update Data");
	}

}
