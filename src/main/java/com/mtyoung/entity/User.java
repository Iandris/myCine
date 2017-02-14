package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "id")
  private int uuid;

  @Column(name="roleID")
  private int roleid;

  @Column(name = "fName")
  private String fname;

  @Column(name = "lName")
  private String lname;

  @Column(name = "id_address")
  private int id_address;

  @Column(name = "email")
  private String email;

  @Column(name = "cell_number", unique = true)
  private String cell_number;

  @Column(name = "password")
  private String password;

  @Column(name = "reminderThreshold")
  private int reminderthreshold;

  @Column(name = "defaultRentalPeriod")
  private int defaultrentalperiod;


  public int getUuid() {
    return uuid;
  }

  public int getRoleid() {
    return roleid;
  }

  public void setRoleid(int roleid) {
    this.roleid = roleid;
  }


  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }


  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }


  public int getAddressid() {
    return id_address;
  }

  public void setAddressid(int addressid) {
    this.id_address = addressid;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCellnumber() {
    return cell_number;
  }

  public void setCellnumber(String cellnumber) {
    this.cell_number = cellnumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public int getReminderthreshold() {
    return reminderthreshold;
  }

  public void setReminderthreshold(int reminderthreshold) {
    this.reminderthreshold = reminderthreshold;
  }


  public int getDefaultrentalperiod() {
    return defaultrentalperiod;
  }

  public void setDefaultrentalperiod(int defaultrentalperiod) {
    this.defaultrentalperiod = defaultrentalperiod;
  }

}
