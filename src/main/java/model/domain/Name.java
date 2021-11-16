package model.domain;

/** A class for member's name. */
public class Name {
  private String name;

  public Name() {}

  public Name(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}
