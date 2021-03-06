package com.mtyoung.entity;
import com.mtyoung.util.LocalDateAttributeConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Movie", uniqueConstraints = {
        @UniqueConstraint(columnNames = "imdbID"),
        @UniqueConstraint(columnNames = "upcCode")
})
public class Movie  implements Serializable{

  private int idmovie;
  private String title;
  private Format format;
  private Genre genre;
  private Director director;
  private Studio studio;
  private String imdbid;
  private String upccode;
  private LocalDate releaseDate;
  private String imgsource;
  private Set<UserMovieLink> links = new HashSet<UserMovieLink>(0);

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idmovie")
  public int getIdmovie() {
    return idmovie;
  }

  public void setIdmovie(int idmovie) {
    this.idmovie = idmovie;
  }

  @Column(name="title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "genre")
  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "format")
  public Format getFormat() {
    return format;
  }

  public void setFormat(Format format) {
    this.format = format;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "director")
  public Director getDirector() {
    return director;
  }

  public void setDirector(Director director) {
    this.director = director;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "studio")
  public Studio getStudio() {
    return studio;
  }

  public void setStudio(Studio studio) {
    this.studio = studio;
  }

  @Column(name="imdbID", unique = true)
  public String getImdbid() {
    return imdbid;
  }

  public void setImdbid(String imdbid) {
    this.imdbid = imdbid;
  }

  @Column(name="upcCode", unique = true)
  public String getUpccode() {
    return upccode;
  }

  public void setUpccode(String upccode) {
    this.upccode = upccode;
  }

  @Column(name="releaseDate")
  @Convert(converter = LocalDateAttributeConverter.class)
  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  @Column(name="imgSource")
  public String getImgsource() {
      return imgsource;
  }

  public void setImgsource(String url) {
      this.imgsource = url;
  }


    @OneToMany (fetch = FetchType.LAZY, mappedBy = "movieid")
    public Set<UserMovieLink> getMovieSet() {
        return this.links;
    }

    public void setMovieSet (Set<UserMovieLink> links) {
        this.links = links;
    }
}
