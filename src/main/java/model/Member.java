package model;

import java.util.LinkedList;
/**
 * Module for the Member class.
 *
 */

public class Member {

  private String name;
  private Long personalNumber;
  private String memberId;
  private LinkedList<Boat> boatList = new LinkedList<>();

  /**
   * An instance of Member class.
   *
   * @param name {string}
   * @param personalNumber {long}
   */
  public Member(String name, Long personalNumber) {
    this.name = name;
    this.personalNumber = personalNumber;
    // create userId with the current time in milliseconds.
    long id = System.currentTimeMillis();
    // change long to string
    String num = Long.toString(id);
    // take only 6 characters/digit for id itself
    num = num.substring(1, 7);
    setMemberId(num);
  }

  public String getName() {
    return name;
  }

  public String getMemberId() {
    return memberId;
  }

  public Long getPersonalNumber() {
    return personalNumber;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public LinkedList<Boat> getBoats() {
    return boatList;
  }

  public void changeName(String name) {
    this.name = name;
  }

  public void changePersonNo(long pernum) {
    this.personalNumber = pernum;
  }

  /**
   * Method to add the boat.
   *
   * @param b {*}
   */
  public void addBoat(Boat b) {
    this.boatList.add(b);
  }

  /**
   * Method to remove the boat.
   *
   * @param b {*}
   */
  public void removeBoat(Boat b) {
    for (int i = 0; i < this.boatList.size(); i++) {
      if (b.getBoatId() == this.boatList.get(i).getBoatId()) {
        this.boatList.remove(i);
        return;
      }
    }
  }
}
