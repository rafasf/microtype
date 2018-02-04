package com.rafasf.microtype.hibernate;

public class PersistentPersonId extends StringMicroTypeUserType<PersonId> {
  public PersistentPersonId() {
    super(PersonId.class);
  }
}
