@TypeDefs(
  @TypeDef(
    name = "personId",
    defaultForType = PersonId.class,
    typeClass = PersistentPersonId.class)
)
package com.rafasf.microtype.hibernate;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
