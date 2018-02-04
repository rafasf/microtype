@TypeDefs(
  @TypeDef(
    name = "personId",
    defaultForType = PersonId.class,
    typeClass = PersistentPersonId.class)
)
package mtype.hibernate;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
