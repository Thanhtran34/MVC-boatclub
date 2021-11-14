package model.domain;

/** A class for name of member. */
public class Name {
  private String name;

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
