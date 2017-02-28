package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Addresses")
public class Address implements Serializable {

  private int idaddresses;
  private String streetaddress1;
  private String streetaddress2;
  private String city;
  private State state;
  private int zipcode;
  private Set<User> userSet = new HashSet<User>(0);


  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idAddresses")
  public int getIdaddresses() {
    return idaddresses;
  }

  public void setIdaddresses(int idaddresses) {
    this.idaddresses = idaddresses;
  }

  @Column(name="StreetAddress1")
  public String getStreetaddress1() {
    return streetaddress1;
  }

  public void setStreetaddress1(String streetaddress1) {
    this.streetaddress1 = streetaddress1;
  }

  @Column(name="StreetAddress2")
  public String getStreetaddress2() {
    return streetaddress2;
  }

  public void setStreetaddress2(String streetaddress2) {
    this.streetaddress2 = streetaddress2;
  }

  @Column(name="City")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

 // @Column(name="state")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "state")
  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  @Column(name="ZipCode")
  public int getZipcode() {
    return zipcode;
  }

  public void setZipcode(int zipcode) {
    this.zipcode = zipcode;
  }

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "address")
    public Set<User> getUserSet() {
        return this.userSet;
    }

    public void setUserSet (Set<User> userSet) {
        this.userSet = userSet;
    }
}
