package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;
import org.apache.catalina.realm.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;

@Entity
@Table(name = "users")
public class User implements Comparable<User>, Serializable{

  private int uuid;
  private String user_name;
  private String fname;
  private String lname;
  private Address address;
  private String cellnumber;
  private int reminderthreshold;
  private int defaultrentalperiod;
  private String password;

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "id")
  public int getUuid() {
    return uuid;
  }

  public void setUuid(int uuid) {
    this.uuid = uuid;
  }

  @Column(name = "fName")
  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }


  @Column(name = "lName")
  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  //@Column(name = "id_address")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "address")
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }


  @Column(name = "user_name")
  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }


  @Column(name = "cell_number", unique = true)
  public String getCellnumber() {
    return cellnumber;
  }

  public void setCellnumber(String cellnumber) {
    this.cellnumber = cellnumber;
  }


  @Column(name = "reminderThreshold")
  public int getReminderthreshold() {
    return reminderthreshold;
  }

  public void setReminderthreshold(int reminderthreshold) {
    this.reminderthreshold = reminderthreshold;
  }


  @Column(name = "defaultRentalPeriod")
  public int getDefaultrentalperiod() {
    return defaultrentalperiod;
  }

  public void setDefaultrentalperiod(int defaultrentalperiod) {
    this.defaultrentalperiod = defaultrentalperiod;
  }


  @Column(name="password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
