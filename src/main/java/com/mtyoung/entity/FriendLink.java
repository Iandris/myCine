package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="FriendLink")
public class FriendLink {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "linkID")
  private int linkID;

  @Column(name="friendID1")
  private int friendid1;

  @Column(name="friendID2")
  private int friendid2;

  public int getLinkid() {
    return linkID;
  }

  public int getFriendid1() {
    return friendid1;
  }

  public void setFriendid1(int friendid1) {
    this.friendid1 = friendid1;
  }

  public int getFriendid2() {
    return friendid2;
  }

  public void setFriendid2(int friendid2) {
    this.friendid2 = friendid2;
  }

}
