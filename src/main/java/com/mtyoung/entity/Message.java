package com.mtyoung.entity;

import java.time.*;

public class Message {

  private int idmessagelog;
  private int senderid;
  private int recipientid;
  private String messagebody;
  private LocalDateTime datetime;


  public int getIdmessagelog() {
    return idmessagelog;
  }

  public void setIdmessagelog(int idmessagelog) {
    this.idmessagelog = idmessagelog;
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
