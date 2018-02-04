package mtype.hibernate;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static mtype.hibernate.PersonId.personId;
import static org.testng.Assert.assertEquals;

public class GenericUserTypeTest {
  private EntityManager entityManager;

  @BeforeClass
  public void setUp() throws Exception {
    entityManager = Persistence
      .createEntityManagerFactory("user-types")
      .createEntityManager();
  }

  @AfterMethod
  public void tearDown() throws Exception {
    entityManager.clear();
    entityManager.close();
    entityManager.getEntityManagerFactory().close();
  }

  @Test
  void persistsMicroTypeAsRawValueAndReadsAsMicroType() throws Exception {
    Person person = new Person(
      personId(UUID.randomUUID().toString()),
      "my name",
      32);

    entityManager.getTransaction().begin();
    entityManager.persist(person);
    entityManager.getTransaction().commit();

    Object result = entityManager.createNativeQuery(
      "select * from person",
      Person.class
    ).getSingleResult();

    assertEquals(result, person);
  }
}
