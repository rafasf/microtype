package mtype.hibernate;

import mtype.MicroType;

import java.io.Serializable;

public class PersonId extends MicroType<String> implements Serializable {
  private PersonId(String value) {
    super(value);
  }

  public static PersonId personId(String value) {
    return new PersonId(value);
  }

  public static PersonId of(String value) {
    return personId(value);
  }
}
