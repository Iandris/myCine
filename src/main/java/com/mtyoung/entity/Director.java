package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Director")
public class Director implements Serializable{

  private int iddirector;
  private String fname;
  private String lname;
  private Set<Movie> movieSet = new HashSet<Movie>(0);


  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idDirector")
  public int getIddirector() {
    return iddirector;
  }

  public void setIddirector(int iddirector) {
    this.iddirector = iddirector;
  }

  @Column(name="fName")
  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  @Column(name="lName")
  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  @OneToMany (fetch = FetchType.LAZY, mappedBy = "director")
  public Set<Movie> getMovieSet() {
    return this.movieSet;
  }

  public void setMovieSet (Set<Movie> movieset) {
    this.movieSet = movieset;
  }
}
