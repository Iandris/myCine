package com.mtyoung.entity;

import com.mtyoung.util.LocalDateTimeAttributeConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name="MessageLog")
public class Message {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idMessageLog")
  private int idmessagelog;

  @Column(name="senderID")
  private int senderid;

  @Column(name="recipientID")
  private int recipientid;

  @Column(name="messageBody")
  private String messagebody;

  @Column(name="dateTime")
  @Convert(converter = LocalDateTimeAttributeConverter.class)
  private LocalDateTime datetime;

  public int getIdmessagelog() {
    return idmessagelog;
  }

  public int getSenderid() {
    return senderid;
  }

  public void setSenderid(int senderid) {
    this.senderid = senderid;
  }

  public int getRecipientid() {
    return recipientid;
  }

  public void setRecipientid(int recipientid) {
    this.recipientid = recipientid;
  }

  public String getMessagebody() {
    return messagebody;
  }

  public void setMessagebody(String messagebody) {
    this.messagebody = messagebody;
  }

  public LocalDateTime getDatetime() {
    return datetime;
  }

  public void setDatetime(LocalDateTime datetime) {
    this.datetime = datetime;
  }

}
