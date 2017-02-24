package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;
import org.apache.catalina.realm.*;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name = "users")
public class User implements Comparable<User>{

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
  private int address;

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

  public int getAddress() {
    return address;
  }

  public void setAddress(int addressid) {
    this.address = addressid;
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

  public int compareTo(User compareUser) {
    int userId = ((User) compareUser).getUuid();

    return this.uuid - userId;
  }

  public static Comparator<User> UserNameComparator = new Comparator<User>() {
    public int compare(User user1, User user2) {
      String lastName1 = user1.getLname().toLowerCase();
      String lastName2 = user2.getLname().toLowerCase();

      return lastName1.compareTo(lastName2);
    }

  };
}
