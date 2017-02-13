package com.mtyoung.entity;


public class Rental {

  private int idrentals;
  private int renterid;
  private int movieid;
  private java.sql.Timestamp duedate;


  public int getIdrentals() {
    return idrentals;
  }

  public void setIdrentals(int idrentals) {
    this.idrentals = idrentals;
  }


  public int getRenterid() {
    return renterid;
  }

  public void setRenterid(int renterid) {
    this.renterid = renterid;
  }


  public int getMovieid() {
    return movieid;
  }

  public void setMovieid(int movieid) {
    this.movieid = movieid;
  }


  public java.sql.Timestamp getDuedate() {
    return duedate;
  }

  public void setDuedate(java.sql.Timestamp duedate) {
    this.duedate = duedate;
  }

}
