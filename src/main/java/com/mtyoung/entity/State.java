package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "States")
public class State implements Serializable {

  private Set<Address> addressSet = new HashSet<Address>(0);
  private int idstate;
  private String shortname;
  private String longname;

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idState")
  public int getIdstate() {
    return idstate;
  }

  public void setIdstate(int idstate) {
    this.idstate = idstate;
  }

  @Column(name="Short_name")
  public String getShortname() {
    return shortname;
  }

  public void setShortname(String short_name) {
    this.shortname = short_name;
  }

  @Column(name="Long_name")
  public String getLongname() {
    return longname;
  }

  public void setLongname(String long_name) {
    this.longname = long_name;
  }

  @OneToMany (fetch = FetchType.EAGER, mappedBy = "state")
  public Set<Address> getAddressSet() {
    return this.addressSet;
  }

  public void setAddressSet (Set<Address> addressSet) {
    this.addressSet = addressSet;
  }
}
