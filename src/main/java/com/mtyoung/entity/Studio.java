package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="Studio")
public class Studio {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idStudio")
  private int idstudio;

  @Column(name="studioTitle")
  private String studiotitle;

  public int getIdstudio() {
    return idstudio;
  }

  public String getStudiotitle() {
    return studiotitle;
  }

  public void setStudiotitle(String studiotitle) {
    this.studiotitle = studiotitle;
  }

}
