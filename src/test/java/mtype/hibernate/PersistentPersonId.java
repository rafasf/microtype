package mtype.hibernate;

public class PersistentPersonId extends StringMicroTypeUserType<PersonId> {
  public PersistentPersonId() {
    super(PersonId.class);
  }
}
