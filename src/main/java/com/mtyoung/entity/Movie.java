package com.mtyoung.entity;
import com.mtyoung.util.LocalDateAttributeConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name="Movie")
public class Movie {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idmovie")
  private int idmovie;

  @Column(name="title")
  private String title;

  @Column(name="format")
  private int format;

  @Column(name="genre")
  private int genre;

  @Column(name="director")
  private int director;

  @Column(name="studio")
  private int studio;

  @Column(name="imdbID", unique = true)
  private String imdbid;

  @Column(name="upcCode", unique = true)
  private String upccode;

  @Column(name="releaseDate")
  @Convert(converter = LocalDateAttributeConverter.class)
  private LocalDate releaseDate;

  public int getIdmovie() {
    return idmovie;
  }

  public void setIdmovie(int idmovie) {
    this.idmovie = idmovie;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getFormat() {
    return format;
  }

  public void setFormat(int format) {
    this.format = format;
  }

  public int getGenre() {
    return genre;
  }

  public void setGenre(int genre) {
    this.genre = genre;
  }

  public int getDirector() {
    return director;
  }

  public void setDirector(int director) {
    this.director = director;
  }

  public int getStudio() {
    return studio;
  }

  public void setStudio(int studio) {
    this.studio = studio;
  }

  public String getImdbid() {
    return imdbid;
  }

  public void setImdbid(String imdbid) {
    this.imdbid = imdbid;
  }

  public String getUpccode() {
    return upccode;
  }

  public void setUpccode(String upccode) {
    this.upccode = upccode;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }
}
