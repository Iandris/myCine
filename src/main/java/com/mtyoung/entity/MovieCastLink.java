package com.mtyoung.entity;


public class MovieCastLink {

  private int idmoviecast;
  private int filmid;
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
