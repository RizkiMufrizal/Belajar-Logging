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
package com.rizki.mufrizal.belajar.logging.service;

import java.util.Date;
import java.util.List;

import com.rizki.mufrizal.belajar.logging.domain.Message;

public interface MessageService {
    public void delete(Message message);

    public Message findByTanggalMasuk(Date date);

    public Message getMessage(String idMessage);

    public List<Message> getMessages();

    public void save(Message message);

    public void update(Message message);
}
