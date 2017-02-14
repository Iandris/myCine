package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="Format")
public class Format {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idFormat")
  private int idformat;

  @Column(name="formatTitle")
  private String formattitle;

  public int getIdformat() {
    return idformat;
  }

  public String getFormattitle() {
    return formattitle;
  }

  public void setFormattitle(String formattitle) {
    this.formattitle = formattitle;
  }

}
