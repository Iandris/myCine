package com.mtyoung.entity;


public class Movie {

  private int idmovie;
  private String title;
  private int format;
  private int genre;
  private int director;
  private int studio;
  private int imdbid;
  private int upccode;


  public int getIdmovie() {
    return idmovie;
  }

  public void setIdmovie(int idmovie) {
    this.idmovie = idmovie;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public int getFormat() {
    return format;
  }

  public void setFormat(int format) {
    this.format = format;
  }


  public int getGenre() {
    return genre;
  }

  public void setGenre(int genre) {
    this.genre = genre;
  }


  public int getDirector() {
    return director;
  }

  public void setDirector(int director) {
    this.director = director;
  }


  public int getStudio() {
    return studio;
  }

  public void setStudio(int studio) {
    this.studio = studio;
  }


  public int getImdbid() {
    return imdbid;
  }

  public void setImdbid(int imdbid) {
    this.imdbid = imdbid;
  }


  public int getUpccode() {
    return upccode;
  }

  public void setUpccode(int upccode) {
    this.upccode = upccode;
  }

}
