package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Format")
public class Format implements Serializable {

  private int idformat;
  private String formattitle;
  private Set<Movie> movieSet = new HashSet<Movie>(0);

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idFormat")
  public int getIdformat() {
    return idformat;
  }

  public void setIdformat(int idformat) {
    this.idformat = idformat;
  }

  @Column(name="formatTitle")
  public String getFormattitle() {
    return formattitle;
  }

  public void setFormattitle(String formattitle) {
    this.formattitle = formattitle;
  }

  @OneToMany (fetch = FetchType.LAZY, mappedBy = "format")
  public Set<Movie> getMovieSet() {
      return this.movieSet;
  }

  public void setMovieSet (Set<Movie> movieset) {
    this.movieSet = movieset;
  }

}
