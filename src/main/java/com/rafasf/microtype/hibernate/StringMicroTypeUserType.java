package com.rafasf.microtype.hibernate;

import com.rafasf.microtype.MicroType;
import org.hibernate.type.StringType;

import java.sql.Types;

public class StringMicroTypeUserType<T extends MicroType<String>>
  extends MicroTypeUserType<String, T> {
  public StringMicroTypeUserType(Class<T> type) {
    super(type, String.class, new StringType());
  }

  @Override
  public int[] sqlTypes() {
    return new int[]{Types.VARCHAR};
  }
}
