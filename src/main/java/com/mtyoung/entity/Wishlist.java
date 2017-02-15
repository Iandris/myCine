package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="WishList")
public class Wishlist {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idWishListLink")
  private int idwishlistlink;

  @Column(name="userID")
  private int userid;

  @Column(name="movieID")
  private int movieid;

  public int getIdwishlistlink() {
    return idwishlistlink;
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

}
