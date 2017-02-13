package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Addresses")
public class Address {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idAddresses")
  private int idaddresses;

  @Column(name="StreetAddress1")
  private String streetaddress1;

  @Column(name="StreetAddress2")
  private String streetaddress2;

  @Column(name="City")
  private String city;

  @Column(name="stateID")
  private int state;

  @Column(name="ZipCode")
  private int zipcode;


  public int getIdaddresses() {
    return idaddresses;
  }

  public void setIdaddresses(int idaddresses) {
    this.idaddresses = idaddresses;
  }


  public String getStreetaddress1() {
    return streetaddress1;
  }

  public void setStreetaddress1(String streetaddress1) {
    this.streetaddress1 = streetaddress1;
  }


  public String getStreetaddress2() {
    return streetaddress2;
  }

  public void setStreetaddress2(String streetaddress2) {
    this.streetaddress2 = streetaddress2;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }


  public int getZipcode() {
    return zipcode;
  }

  public void setZipcode(int zipcode) {
    this.zipcode = zipcode;
  }

}
