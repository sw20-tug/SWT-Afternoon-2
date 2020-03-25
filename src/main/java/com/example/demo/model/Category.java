package com.example.demo.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private String name;

  public Category(String name) {
    this.name = name;
  }

  public Category() {
    this.name = "";
  }

  public String getName()
  {
    return this.name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
}
