package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "UserFriends")
public class UserFriends {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "id")
  private int idConnector;

  @Column(name="friend_a")
  private int friend_a;

  @Column(name="friend_b")
  private int friend_b;

  public int getIdConnector() {
    return idConnector;
  }

  public int getFriendidA() {
    return friend_a;
  }

  public void setFriendidA(int id_1) {
    this.friend_a = id_1;
  }

  public int getFriendidB() {
    return friend_b;
  }

  public void setFriendidB(int id_2) {
    this.friend_b = id_2;
  }

}
