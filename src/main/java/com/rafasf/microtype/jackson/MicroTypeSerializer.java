package com.rafasf.microtype.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.rafasf.microtype.MicroType;

import java.io.IOException;

public class MicroTypeSerializer extends StdSerializer<MicroType<Object>> {
  @SuppressWarnings("unused")
  MicroTypeSerializer() {
    super(MicroType.class, true);
  }

  @Override
  public void serialize(
    MicroType<Object> value,
    JsonGenerator generator,
    SerializerProvider provider
  ) throws IOException {
    generator.writeObject(value.value());
  }
}
