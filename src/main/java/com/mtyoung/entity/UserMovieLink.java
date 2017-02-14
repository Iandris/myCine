package com.mtyoung.entity;


public class UserMovieLink {

  private int linkid;
  private int userid;
  private int movieid;
  private int quantity;
  private int starrating;


  public int getLinkid() {
    return linkid;
  }

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public int getMovieid() {
    return movieid;
  }

  public void setMovieid(int movieid) {
    this.movieid = movieid;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getStarrating() {
    return starrating;
  }

  public void setStarrating(int starrating) {
    this.starrating = starrating;
  }

}
