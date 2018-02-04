package mtype.hibernate;

import mtype.MicroType;
import org.apache.commons.beanutils.MethodUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.Type;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public abstract class MicroTypeUserType<S, T extends MicroType<S>>
  extends ImmutableUserType {
  private final Class<T> microTypeType;
  private final Class<S> sourceType;
  private final Type hibernateType;

  public MicroTypeUserType(
    Class<T> microTypeType,
    Class<S> sourceType,
    Type hibernateType
  ) {
    this.microTypeType = microTypeType;
    this.sourceType = sourceType;
    this.hibernateType = hibernateType;
  }

  @Override
  public abstract int[] sqlTypes();

  public final Class<T> returnedClass() {
    return microTypeType;
  }

  @SuppressWarnings("unchecked")
  public final Object nullSafeGet(
    ResultSet resultSet,
    String[] names,
    SharedSessionContractImplementor session,
    Object owner
  ) throws HibernateException, SQLException {
    try {
      Object value = resultSet.getObject(names[0]);
      return MethodUtils.invokeExactStaticMethod(
        returnedClass(),
        "of",
        new Object[]{value},
        new Class[]{sourceType});
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("unchecked")
  public final void nullSafeSet(
    PreparedStatement preparedStatement,
    Object value,
    int index,
    SharedSessionContractImplementor session
  ) throws HibernateException, SQLException {
    T microType = (T) value;

    if (Objects.isNull(microType) || Objects.isNull(microType.value())) {
      hibernateType.nullSafeSet(preparedStatement, null, index, session);
    } else {
      hibernateType.nullSafeSet(preparedStatement, microType.value(), index, session);
    }
  }
}
