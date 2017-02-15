package com.mtyoung.entity;

import com.mtyoung.util.LocalDateTimeAttributeConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Rentals")
public class Rental {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name = "idRentals")
  private int idrentals;

  @Column(name="renterID")
  private int renterid;

  @Column(name="movieID")
  private int movieid;

  @Column(name="dueDate")
  @Convert(converter = LocalDateTimeAttributeConverter.class)
  private LocalDateTime duedate;

  public int getIdrentals() {
    return idrentals;
  }

  public int getRenterid() {
    return renterid;
  }

  public void setRenterid(int renterid) {
    this.renterid = renterid;
  }

  public int getMovieid() {
    return movieid;
  }

  public void setMovieid(int movieid) {
    this.movieid = movieid;
  }

  public LocalDateTime getDuedate() {
    return duedate;
  }

  public void setDuedate(LocalDateTime duedate) {
    this.duedate = duedate;
  }

}
