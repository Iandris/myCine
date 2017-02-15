package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="UserMovie")
public class UserMovieLink {


  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "linkID")
  private int linkid;

  @Column(name="userID")
  private int userid;

  @Column(name="movieID")
  private int movieid;

  @Column(name="quantity")
  private int quantity;

  @Column(name="starRating")
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
