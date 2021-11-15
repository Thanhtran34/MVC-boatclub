package model.domain;

/** A class for personal number of each member. */
public class PersonalNumber {
  private String personnumber;

  public PersonalNumber() {}

  public PersonalNumber(String personnumber) {
    this.personnumber = personnumber;
  }

  public String getNumber() {
    return personnumber;
  }

  /**
   * Method to check if the personal number is right format.
   *
   */
  public boolean isValidPerNumber() {
    if (personnumber.matches("\\d+") && personnumber.length() == 12) {
      return true;
    }
    return false;
  }
}
