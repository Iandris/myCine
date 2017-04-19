package com.mtyoung.entity;

import com.mtyoung.util.LocalDateTimeAttributeConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Rentals")
public class Rental {

  private int idrentals;
  private User renterid;
  private UserMovieLink movieid;
  private LocalDateTime duedate;

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idRentals")
  public int getIdrentals() {
    return idrentals;
  }

  public void setIdrentals(int idrentals) { this.idrentals = idrentals;}

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "renterID")
  public User getRenterid() {
    return renterid;
  }

  public void setRenterid(User renterid) {
    this.renterid = renterid;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movieID")
  public UserMovieLink getMovieid() {
    return movieid;
  }

  public void setMovieid(UserMovieLink movieid) {
    this.movieid = movieid;
  }

  @Column(name="dueDate")
  @Convert(converter = LocalDateTimeAttributeConverter.class)
  public LocalDateTime getDuedate() {
    return duedate;
  }

  public void setDuedate(LocalDateTime duedate) {
    this.duedate = duedate;
  }

}
