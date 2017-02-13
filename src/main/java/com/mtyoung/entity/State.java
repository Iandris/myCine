package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "States")
public class State {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idState")
  private int idstate;

  @Column(name="Short_name")
  private String short_name;

  @Column(name="Long_name")
  private String long_name;


  public int getIdstate() {
    return idstate;
  }

  public void setIdstate(int idstate) {
    this.idstate = idstate;
  }


  public String getShort() {
    return short_name;
  }

  public void setShort(String short_name) {
    this.short_name = short_name;
  }


  public String getLong() {
    return long_name;
  }

  public void setLong(String long_name) {
    this.long_name = long_name;
  }

}
