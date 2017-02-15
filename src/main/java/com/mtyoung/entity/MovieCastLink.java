package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="MovieCast")
public class MovieCastLink {

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name="idMovieCast")
  private int idmoviecast;

  @Column(name="filmID")
  private int filmid;

  @Column(name="actorID")
  private int actorid;

  public int getIdmoviecast() {
    return idmoviecast;
  }

  public int getFilmid() {
    return filmid;
  }

  public void setFilmid(int filmid) {
    this.filmid = filmid;
  }

  public int getActorid() {
    return actorid;
  }

  public void setActorid(int actorid) {
    this.actorid = actorid;
  }

}
