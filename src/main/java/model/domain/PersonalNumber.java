package model.domain;

/** A class for personal number of member. */
public class PersonalNumber {
  private String pnr;

  public PersonalNumber() {}

  public PersonalNumber(String pnr) {
    this.pnr = pnr;
  }

  public String getString() {
    return pnr;
  }

  /**
   * Method to check valid personal number.
   *
   */
  public boolean isValidNumber() {
    if (pnr.matches("\\d+") && pnr.length() == 12) {
      return true;
    }
    return false;
  }
}
