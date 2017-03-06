package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="friendrequests")
public class Friendrequests implements Serializable {

  private int idfriendrequests;
  private String reqid;
  private int usera;
  private int userb;

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idfriendrequests")
  public int getIdfriendrequests() {
    return idfriendrequests;
  }

  public void setIdfriendrequests(int idfriendrequests) {
    this.idfriendrequests = idfriendrequests;
  }

  @Column(name="reqid")
  public String getReqid() {
    return reqid;
  }

  public void setReqid(String reqid) {
    this.reqid = reqid;
  }

  @Column(name="usera")
  public int getUsera() {
    return usera;
  }

  public void setUsera(int usera) {
    this.usera = usera;
  }

  @Column(name="userb")
  public int getUserb() {
    return userb;
  }

  public void setUserb(int userb) {
    this.userb = userb;
  }

}
