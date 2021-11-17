package model.domain;

import java.util.ArrayList;
import java.util.UUID;
import controller.exception.BoatNotFound;
import model.domain.Boat.BoatType;

/** The Member class to work with member's data. */
public class Member {
  private String name;
  private String personalNumber;
  private String memberId;
  private ArrayList<Boat> boatList = new ArrayList<>();

  /** An default instance of Member class. */
  public Member() {}

  /**
   * An instance of Member class.
   *
   * @param name {string} - name of member.
   * @param personnumber {string} - personal number of member.
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

  public void setName(String newName) {
    name = newName;
  }

  public String getMemberId() {
    return memberId;
  }

  public String getPersonalNumber() {
    return personalNumber;
  }

  public void setPersonalNumber(String pernum) {
    this.personalNumber = pernum;
  }

  public Iterable<Boat> getBoats() {
    return boatList;
  }

  public int getNumberOfBoats() {
    return boatList.size();
  }

  public void changeName(String name) {
    this.name = name;
  }

  public Boat getOneBoat(int position) throws BoatNotFound {
    hasBoat(position);
    return boatList.get(position);
  }

  public Boat[] getAllBoats() {
    Boat[] boats = new Boat[boatList.size()];
    return boatList.toArray(boats);
  }

  public void registerBoat(BoatType type, double lengthInFeet) {
    Boat boat = new Boat(type, lengthInFeet);
    boatList.add(boat);
  }

  public void updateBoat(double length, BoatType type, Boat boat) {
    boat.setLength(length);
    boat.setType(type);
  }

  public void deleteBoat(int position) throws BoatNotFound {
    hasBoat(position);
    boatList.remove(position);
  }

  private void hasBoat(int position) throws BoatNotFound {
    if (boatList.size() <= position) {
      throw new BoatNotFound("Boat Not Found!");
    }
  }
}
