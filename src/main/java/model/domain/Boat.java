package model.domain;

import java.util.concurrent.atomic.AtomicInteger;

/** Module for the Boat class. */
public class Boat {
  /** Enumeration for boat type. */
  public enum BoatType {
    Sailboat,
    Motorsailer,
    KayakCanoe,
    Other;
  }

  // static id generator shared among all instances of Coordinates
  private static final AtomicInteger idGenerator = new AtomicInteger(1000);

  private String owner;
  private double lengthInFeet;
  private double lengthInMeters;
  private int boatId;
  private BoatType type;

  public Boat() {}

  /**
   * An instance of the Boat class.
   *
   * @param owner {Integer}
   * @param type {*}
   */
  public Boat(String owner, BoatType type, double lengthInFeet) {
    this.owner = owner;
    this.type = type;
    this.lengthInFeet = lengthInFeet;
    this.lengthInMeters = getLengthInMeters();
    this.boatId = idGenerator.getAndIncrement();
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

  public void setType(BoatType type) {
    this.type = type;
  }

  public int getBoatId() {
    return boatId;
  }

  public BoatType getType() {
    return type;
  }

  /** Method to get length in meters. */
  public double getLengthInMeters() {
    return this.lengthInFeet * 0.3048;
  }
}
