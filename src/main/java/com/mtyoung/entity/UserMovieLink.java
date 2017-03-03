package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="UserMovie")
public class UserMovieLink implements Serializable {

  private int linkid;
  private User userid;
  private Movie movieid;
  private int quantity;
  private int starrating;
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "linkID")
  public int getLinkid() {
    return linkid;
  }

  public void setLinkid(int linkid) {
    this.linkid = linkid;
  }


  //@Column(name="userID")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userid")
  public User getUserid() {
    return userid;
  }

  public void setUserid(User userid) {
    this.userid = userid;
  }


 // @Column(name="movieID")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movieid")
  public Movie getMovieid() {
    return movieid;
  }

  public void setMovieid(Movie movieid) {
    this.movieid = movieid;
  }


  @Column(name="quantity")
  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }


  @Column(name="starRating")
  public int getStarrating() {
    return starrating;
  }

  public void setStarrating(int starrating) {
    this.starrating = starrating;
  }

}
