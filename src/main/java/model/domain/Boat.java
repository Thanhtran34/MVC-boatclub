package model.domain;

import java.util.concurrent.ThreadLocalRandom;

/** The Boat class to work with boat's data. */
public class Boat {
  /** Enumeration for boat type. */
  public enum BoatType {
    Sailboat,
    Motorsailer,
    KayakCanoe,
    Other;
  }

  private String owner;
  private double lengthInFeet;
  private double lengthInMeters;
  private int boatId;
  private BoatType type;

  public Boat() {}

  /**
   * An instance of the Boat class.
   *
   * @param owner {String} - the owner of the boat-memberId.
   * @param type {*} the type of the boat.
   * @param lengthInFeet {*} the length in feet of the boat.
   */
  public Boat(String owner, BoatType type, double lengthInFeet) {
    this.owner = owner;
    this.type = type;
    this.lengthInFeet = lengthInFeet;
    this.lengthInMeters = getLengthInMeters();
    this.boatId = ThreadLocalRandom.current().nextInt(4000 + 1);
  }

  public String getOwner() {
    return owner;
  }

  public double getLength() {
    return lengthInFeet;
  }

  public void setLength(double length) {
    this.lengthInFeet = length;
  }

  public BoatType getType() {
    return type;
  }

  public void setType(BoatType type) {
    this.type = type;
  }

  public int getBoatId() {
    return boatId;
  }

  /** Method to get length in meters. */
  public double getLengthInMeters() {
    return this.lengthInFeet * 0.3048;
  }
}
