package com.rizki.mufrizal.belajar.logging.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rizki.mufrizal.belajar.logging.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
	public Message findBytanggalMasuk(Date date);
}
