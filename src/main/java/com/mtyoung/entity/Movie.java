package com.mtyoung.entity;
import com.mtyoung.util.LocalDateAttributeConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Movie", uniqueConstraints = {
        @UniqueConstraint(columnNames = "imdbID"),
        @UniqueConstraint(columnNames = "upcCode")
})
public class Movie  implements Comparable<Movie>, Serializable{


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

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "genre")
  //@Column(name="genre")
  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "format")
  public Format getFormat() {
    return format;
  }

  public void setFormat(Format format) {
    this.format = format;
  }

  //@Column(name="director")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "director")
  public Director getDirector() {
    return director;
  }

  public void setDirector(Director director) {
    this.director = director;
  }

 // @Column(name="studio")
  @ManyToOne(fetch = FetchType.EAGER)
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


    @OneToMany (fetch = FetchType.EAGER, mappedBy = "movieid")
    public Set<UserMovieLink> getMovieSet() {
        return this.links;
    }

    public void setMovieSet (Set<UserMovieLink> links) {
        this.links = links;
    }

  public int compareTo(Movie compareMovie) {
    int movieID = ((Movie) compareMovie).getIdmovie();

    return this.idmovie - movieID;
  }

  public static Comparator<Movie> MovieNameComparator = new Comparator<Movie>() {
    public int compare(Movie movie1, Movie movie2) {
      String movieName1 = movie1.getTitle().toLowerCase();
      String movieName2 = movie2.getTitle().toLowerCase();

      return movieName1.compareTo(movieName2);
    }

  };
}
