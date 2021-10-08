package model.domain;

import java.util.LinkedList;
import java.util.UUID;

/** The Member class to work with member's data. */
public class Member {

  private String name;
  private String personalNumber;
  private String memberId;
  private LinkedList<Boat> boatList = new LinkedList<>();

  /** An default instance of Member class. */
  public Member() {}

  /**
   * An instance of Member class.
   *
   * @param name {string} - name of member.
   * @param personnumber {long} - personal number of member.
   */
  public Member(String name, String personnumber) {
    this.name = name;
    this.personalNumber = personnumber;
    // create unique id with 6 characters
    UUID id = UUID.randomUUID();
    this.memberId = id.toString().substring(0, 6);
  }

  public String getName() {
    return name;
  }

  public String getMemberId() {
    return memberId;
  }

  public String getPersonalNumber() {
    return personalNumber;
  }

  public LinkedList<Boat> getBoats() {
    return boatList;
  }

  public void changeName(String name) {
    this.name = name;
  }

  public void changePersonNo(String pernum) {
    this.personalNumber = pernum;
  }
}
