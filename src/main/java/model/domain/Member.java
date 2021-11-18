package model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import exception.BoatNotFound;
import java.util.ArrayList;
import model.domain.Boat.BoatType;

/** The Member class to work with member's data. */
public class Member {
  private String name;
  private String memberId;
  private String personalNumber;
  private ArrayList<Boat> boatList = new ArrayList<>();

  /** An default instance of Member class. */
  public Member() {}

  /**
   * An instance of Member class.
   *
   * @param name {string} - name of member.
   * @param personnumber {string} - personal number of member.
   */
  public Member(String name, String personnumber, String id) {
    this.name = name;
    this.personalNumber = personnumber;
    this.memberId = id;
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

  public ArrayList<Boat> getBoats() {
    return boatList;
  }

  @JsonIgnore
  public int getNumberOfBoats() {
    return boatList.size();
  }

  @JsonIgnore
  public Boat getOneBoat(int position) throws BoatNotFound {
    return boatList.get(position);
  }

  public void addBoat(String owner, BoatType type, double lengthInFeet) {
    Boat boat = new Boat(owner, type, lengthInFeet);
    boatList.add(boat);
  }

  public void editBoat(double length, BoatType type, Boat boat) {
    boat.setLength(length);
    boat.setType(type);
  }

  public void removeBoat(int idx) throws BoatNotFound {
    boatList.remove(idx);
  }

  /**
   * Method to check if the boat exist or not.
   * 
   */
  public boolean isBoatExist(int id) {
    for (Boat b : boatList) {
      if (b.getBoatId() == id) {
        return true;
      }
    }
    return false;
  }
}
