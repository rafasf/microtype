# MicroType

Do you know when there are methods and constructors around the codebase that looks like `SomeClass(String one, String two, String three)` and it is impossible to know what is what? Well, the `MicroType` might be able to help with that.

`MicroType` is a wrapper class around a "primitive" single value. For example, if you have a property called `accountNumber` which is a `String` you can create a type for it by extending the `MicroType` class.

The original class:

```java
public class Account {
  String owner;
  String accountNumber;
}
```
Let's create the account number `MicroType`:

```java
public class AccountNumber extends MicroType<String> {
  private AccountNumber(String value) {
    super(value);
  }
  
  public static AccountNumber accountNumber(String value) {
    return new AccountNumber(value);
  }
}
```

And now, replace the `String` with `AccountNumber`:

```java
public class Account {
  String owner;
  AccountNumber accountNumber;
}
```
Now you have a type that can explicitly describe your domain as well as contain its related logic.

## Jackson Serialization and Deserialization

Just include the `MicroTypeModule` to the Jackson's `ObjectMapper` configuration.

## Hibernate

That's slightly more work! _(surprise)_

TODO: write the steps to use `UserType`.
