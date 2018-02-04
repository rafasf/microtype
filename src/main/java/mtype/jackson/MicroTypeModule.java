package mtype.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import static java.util.Collections.singletonList;

public class MicroTypeModule extends SimpleModule {
  public MicroTypeModule() {
    super(
      "MicroTypeModule",
      Version.unknownVersion(),
      singletonList(new MicroTypeSerializer()));
  }
}
