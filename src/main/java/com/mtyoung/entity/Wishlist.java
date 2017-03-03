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

  //@Column(name="userID")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "userid")
  public User getUserid() {
    return userid;
  }

  public void setUserid(User userid) {
    this.userid = userid;
  }

  //@Column(name="movieID")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "movieid")
  public Movie getMovieid() {
    return movieid;
  }

  public void setMovieid(Movie movieid) {
    this.movieid = movieid;
  }

}
