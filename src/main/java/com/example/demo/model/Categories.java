package com.example.demo.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Categories {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private String name;

  public Categories(String name) {
    this.name = name;
  }

  public Categories() {
    this.name = "";
  }
}
