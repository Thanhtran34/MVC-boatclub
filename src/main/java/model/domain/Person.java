package model.domain;

/** A class to represent a member's profile. */
public class Person {
  private Name name;
  private PersonalNumber pernum;

  public Person() {}

  public Person(Name name, PersonalNumber pernum) {
    this.name = name;
    this.pernum = pernum;
  }

  public Name getName() {
    return name;
  }

  public PersonalNumber getPersonalNumber() {
    return pernum;
  }
}
