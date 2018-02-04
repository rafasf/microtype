package com.rafasf.microtype.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
  @Id
  @Column(columnDefinition = "char(36)")
  PersonId id;
  String name;
  Integer age;

  // Required by Jackson
  private Person() {
  }

  public Person(PersonId id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", age=" + age +
      '}';
  }
}
