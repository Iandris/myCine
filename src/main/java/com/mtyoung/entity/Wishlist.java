package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="WishList")
public class Wishlist {

  private int idwishlistlink;
  private User userid;
  private Movie movieid;


  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idWishListLink")
  public int getIdwishlistlink() {
    return idwishlistlink;
  }

  public void setIdwishlistlink(int wishlistid) {
    this.idwishlistlink = wishlistid;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userid")
  public User getUserid() {
    return userid;
  }

  public void setUserid(User userid) {
    this.userid = userid;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movieid")
  public Movie getMovieid() {
    return movieid;
  }

  public void setMovieid(Movie movieid) {
    this.movieid = movieid;
  }

}
