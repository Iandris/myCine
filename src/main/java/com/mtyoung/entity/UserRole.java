package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "UserRoles")
public class UserRole {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "roleID")
  private int roleid;

  @Column(name = "user_name")
  private String user_name;

  @Column(name= "role_name")
  private String role_name;

  public int getRoleid() {
    return roleid;
  }

  public String getuser_name() {
    return user_name;
  }

  public void setuser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getRole_name() {
    return role_name;
  }

  public void setRole_name(String role_name) {
    this.role_name = role_name;
  }
}
