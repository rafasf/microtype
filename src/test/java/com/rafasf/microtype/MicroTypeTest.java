package com.rafasf.microtype;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.rafasf.microtype.MyType.myType;
import static org.testng.Assert.*;

public class MicroTypeTest {
  @Test
  void returnsTheWrappedValue() {
    assertEquals("the-value", myType("the-value").value());
  }

  @Test
  void supportsNullValues() {
    assertNull(myType(null).value());
  }

  @Test
  void printsValueOnToString() {
    assertEquals("the-value", myType("the-value").toString());
  }

  @Test
  void returnsNullStringOnToStringIfValueIsNull() {
    assertEquals("null", myType(null).toString());
  }

  @Test
  void areEqualIfValuesAreTheSame() {
    MyType someType = myType("yay");

    assertEquals(someType, someType);
    Assert.assertEquals(myType("some value here"), myType("some value here"));
    Assert.assertEquals(myType(null), myType(null));
  }

  @Test
  void areNotEqualIfValuesAreDifferent() {
    assertNotEquals(myType("value one"), myType("value two"));
  }
}

class MyType extends MicroType<String> {
  private MyType(String value) {
    super(value);
  }

  static MyType myType(String value) {
    return new MyType(value);
  }
}
