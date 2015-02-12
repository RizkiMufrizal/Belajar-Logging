/**
 *    Copyright 2015 Rizki Mufrizal <mufrizalrizki@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.rizki.mufrizal.belajar.logging.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rizki.mufrizal.belajar.logging.domain.Message;
import com.rizki.mufrizal.belajar.logging.repository.MessageRepository;
import com.rizki.mufrizal.belajar.logging.service.MessageService;

@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageRepository messageRepository;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(MessageServiceImpl.class);

    @Transactional(readOnly = false)
    @Override
    public void delete(Message message) {
        messageRepository.delete(message);
    }

    @Override
    public Message findByTanggalMasuk(Date date) {
        return messageRepository.findBytanggalMasuk(date);
    }

    @Override
    public Message getMessage(String idMessage) {
        return messageRepository.findOne(idMessage);
    }

    @Override
    public List<Message> getMessages() {
        LOGGER.debug("get All Data Message");
        return messageRepository.findAll();
    }

    @Transactional(readOnly = false)
    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Message message) {
        messageRepository.saveAndFlush(message);
    }

}
