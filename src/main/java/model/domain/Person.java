package model.domain;

/** A class to represent information of one person. */
public class Person {
  private Name name;
  private PersonalNumber pnr;

  public Person(Name name, PersonalNumber pnr) {
    this.name = name;
    this.pnr = pnr;
  }

  public Name getName() {
    return name;
  }

  public PersonalNumber getPersonalNumber() {
    return pnr;
  }

  protected void setName(Name changedInfo) {
    name = changedInfo;
  }

  protected void setPersonalNumber(PersonalNumber changedInfo) {
    pnr = changedInfo;
  }
}
