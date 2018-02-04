package mtype.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import mtype.MicroType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

import static mtype.jackson.SomeType.someType;
import static org.testng.Assert.assertEquals;

public class MicroTypeSerializerTest {
  private ObjectMapper objectMapper;

  @BeforeMethod
  public void setUp() throws Exception {
    objectMapper = new ObjectMapper()
      .registerModule(new MicroTypeModule());
  }

  @Test
  void serializesToRawValue() throws Exception {
    SomeType someType = someType("the-value");

    assertEquals(
      objectMapper.writeValueAsString(someType),
      "\"the-value\"");
  }

  @Test
  void serializesToRawValueWhenNested() throws Exception {
    assertEquals(
      objectMapper.writeValueAsString(
        new Container("something in here", someType("valuable info"))),
      "{\"fieldOne\":\"something in here\",\"someType\":\"valuable info\"}");
  }

  @Test
  void deserializeToRawValueIntoProperty() throws Exception {
    assertEquals(
      objectMapper.readValue(
        "\"valuable info\"",
        SomeType.class),
      someType("valuable info"));
  }

  @Test
  void deserializeToRawValueIntoNestedProperty() throws Exception {
    assertEquals(
      objectMapper.readValue(
        "{\"fieldOne\":\"something in here\",\"someType\":\"valuable info\"}",
        Container.class),
      new Container("something in here", someType("valuable info")));
  }
}

@SuppressWarnings("ALL")
class Container {
  public final String fieldOne;
  public final SomeType someType;

  private Container() {
    this(null, null);
  }

  public Container(String fieldOne, SomeType someType) {
    this.fieldOne = fieldOne;
    this.someType = someType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Container container = (Container) o;
    return Objects.equals(fieldOne, container.fieldOne) &&
      Objects.equals(someType, container.someType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fieldOne, someType);
  }
}

class SomeType extends MicroType<String> {
  private SomeType(String value) {
    super(value);
  }

  static SomeType someType(String value) {
    return new SomeType(value);
  }
}
