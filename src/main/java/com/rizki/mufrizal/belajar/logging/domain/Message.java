package com.rizki.mufrizal.belajar.logging.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "tb_message")
public class Message implements Serializable {

    private static final long serialVersionUID = -6093321383634839287L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "idMessage")
    private String idMessage;

    @Column(name = "message", length = 30)
    private String message;

    @Column(name = "tujuan", length = 30)
    private String tujuan;

    @JsonFormat(shape = Shape.STRING, pattern = "dd/mm/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "tanggalMasuk")
    private Date tanggalMasuk;

    public String getIdMessage() {
        return idMessage;
    }

    public String getMessage() {
        return message;
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setIdMessage(String idMessage) {
        this.idMessage = idMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
}
