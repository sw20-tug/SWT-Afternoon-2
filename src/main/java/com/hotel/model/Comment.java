package com.hotel.model;

import javax.persistence.*;
import javax.validation.constraints.Null;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private String comm_text;

  @Column
  private String user_name;

  @Column
  private int rate;

  @Column
  private long hotel_id;

  public Comment() {}

  public Comment(String comment) {this.comm_text = comment;}

  public Comment(String comment, String user_name, int rate, long hotel_id) {
    this.comm_text = comment;
    this.user_name = user_name;
    this.rate = rate;
    this.hotel_id = hotel_id;
  }

  public void setComm_text(String comment)
  {
    this.comm_text = comment;
  }

  public void setUser_name(String user_name)
  {
    this.user_name = user_name;
  }

  public void setRate(int rate)
  {
    this.rate = rate;
  }

  public void setHotel_id(long hotel_id)
  {
    this.hotel_id = hotel_id;
  }

  public String getComm_text(){return this.comm_text;}
  public String getUser_name(){return this.user_name;}
  public int getRate(){return this.rate;}
  public long getHotelID(){return this.hotel_id;}




}
