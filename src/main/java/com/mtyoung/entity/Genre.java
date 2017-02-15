package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="Genre")
public class Genre {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idGenre")
  private int idgenre;

  @Column(name="genreTitle")
  private String genretitle;

  public int getIdgenre() {
    return idgenre;
  }

  public String getGenretitle() {
    return genretitle;
  }

  public void setGenretitle(String genretitle) {
    this.genretitle = genretitle;
  }

}
