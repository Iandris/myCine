package com.mtyoung.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Studio")
public class Studio {

  private int idstudio;
  private String studiotitle;
  private Set<Movie> movieSet = new HashSet<Movie>(0);

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idStudio")
  public int getIdstudio() {
    return idstudio;
  }

  public void setIdstudio(int idstudio) {
    this.idstudio = idstudio;
  }


  @Column(name="studioTitle")
  public String getStudiotitle() {
    return studiotitle;
  }

  public void setStudiotitle(String studiotitle) {
    this.studiotitle = studiotitle;
  }

  @OneToMany (fetch = FetchType.EAGER, mappedBy = "studio")
  public Set<Movie> getMovieSet() {
    return this.movieSet;
  }

  public void setMovieSet (Set<Movie> movieset) {
    this.movieSet = movieset;
  }
}
