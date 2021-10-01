package model.domain;

import java.util.LinkedList;
import java.util.UUID;

/** Module for the Member class. */
public class Member {

  private String name;
  private Long personalNumber;
  private String memberId;
  private LinkedList<Boat> boatList = new LinkedList<Boat>();

  /**
   * An default instance of Member class.
   *
   */
  public Member() {}

  /**
   * An instance of Member class.
   *
   * @param name {string} - name of member.
   * @param personnumber {long} - personal number of member.
   */
  public Member(String name, long personnumber) {
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

  public Long getPersonalNumber() {
    return personalNumber;
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
   * @param b {*} - Boat owned by member.
   */
  public void addBoat(Boat b) {
    this.boatList.add(b);
  }

  /**
   * Method to remove the boat.
   *
   * @param b {*} - Boat removed by member.
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
