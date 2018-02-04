package mtype.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.util.Objects;

public abstract class ImmutableUserType implements UserType {
  public boolean equals(Object x, Object y) throws HibernateException {
    return Objects.equals(x, y);
  }

  public int hashCode(Object x) throws HibernateException {
    return x.hashCode();
  }

  public Object deepCopy(Object value) throws HibernateException {
    return value;
  }

  public boolean isMutable() {
    return false;
  }

  public Serializable disassemble(Object value) throws HibernateException {
    return (Serializable) value;
  }

  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    return cached;
  }

  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return original;
  }
}
