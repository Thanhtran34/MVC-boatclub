package model.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

/** The Member class to work with member's data. */
public class Member extends Person {
  private MemberId memberId;
  private ArrayList<Boat> boatList;

  /** An default instance of Member class. */
  public Member(Name name, PersonalNumber pnr) {
    super(name, pnr);
    this.memberId = new MemberId();
    boatList = new ArrayList<>();
  }

  public Member(Name name, PersonalNumber pnr, MemberId id) {
    super(name, pnr);
    this.memberId = id;
    boatList = new ArrayList<>();
  }

  public Member(Person person) {
    super(person.getName(), person.getPersonalNumber());
    this.memberId = new MemberId();
    boatList = new ArrayList<>();
  }

  /**
   * Get the unique member id.
   *
   * @return the member id.
   */
  public MemberId getMemberId() {
    return memberId;
  }

  /** Method to get all owned boats of one member. */
  public Iterable<Boat> getBoats() {
    return boatList;
  }

  /** Method to get the number of owned boat. */
  public int getNumberOfBoats() {
    return boatList.size();
  }

  /**
   * Adds a new boat to the member.
   *
   * @param t BoatType of Boat to add.
   * @param lengthInFeet The length of the boat in feet.
   */
  public Boat addBoat(Boat.BoatType t, double lengthInFeet) {
    Boat ret = new Boat(t, lengthInFeet);
    this.boatList.add(ret);

    return ret;
  }

  /**
   * Call to remove a boat from the member.
   *
   * @param selectedBoat the boat object to remove.
   */
  public void deleteBoat(Boat selectedBoat) {
    boatList.remove(selectedBoat);
  }

  /**
   * Sets the personal information (name and personal number).
   *
   * @param changedInfo the information to set.
   */
  public void setInfo(Person changedInfo) {
    this.setPersonalNumber(changedInfo.getPersonalNumber());
    this.setName(changedInfo.getName());
  }
}
