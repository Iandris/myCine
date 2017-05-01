package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Genre")
public class Genre implements Serializable {
  private int idgenre;
  private String genretitle;
  private Set<Movie> movieSet = new HashSet<Movie>(0);

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idGenre")
  public int getIdgenre() {
    return idgenre;
  }

  public void setIdgenre(int idgenre) {
    this.idgenre = idgenre;
  }

  @Column(name="genreTitle")
  public String getGenretitle() {
    return genretitle;
  }

  public void setGenretitle(String genretitle) {
    this.genretitle = genretitle;
  }

  @OneToMany (fetch = FetchType.LAZY, mappedBy = "genre")
  public Set<Movie> getMovieSet() {
    return this.movieSet;
  }

  public void setMovieSet (Set<Movie> movieset) {
    this.movieSet = movieset;
  }
}
