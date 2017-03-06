package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "UserFriends")
public class UserFriends {
  private int idConnector;
  private int frienda;
  private int friendb;

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "id")
  public int getIdConnector() {
    return idConnector;
  }

  public void setIdConnector(int idConnector) {
    this.idConnector = idConnector;
  }

  @Column(name="frienda")
  public int getFrienda() {
    return frienda;
  }

  public void setFrienda(int frienda) {
    this.frienda = frienda;
  }

  @Column(name="friendb")
  public int getFriendb() {
    return friendb;
  }

  public void setFriendb(int friendb) {
    this.friendb = friendb;
  }

}
