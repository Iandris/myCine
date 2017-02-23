package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;
import org.apache.catalina.realm.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "id")
  private int uuid;

  @Column(name = "user_name")
  private String user_name;

  @Column(name = "fName")
  private String fname;

  @Column(name = "lName")
  private String lname;

  @Column(name = "id_address")
  private int id_address;

  @Column(name = "cell_number", unique = true)
  private String cellnumber;

  @Column(name = "reminderThreshold")
  private int reminderthreshold;

  @Column(name = "defaultRentalPeriod")
  private int defaultrentalperiod;

  @Column(name="password")
  private String password;

  public int getUuid() {
    return uuid;
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

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getCellnumber() {
    return cellnumber;
  }

  public void setCellnumber(String cellnumber) {
    this.cellnumber = cellnumber;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = RealmBase.Digest(password,"sha-256", "UTF-8");
  }
}
