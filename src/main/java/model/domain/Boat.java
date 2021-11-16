package model.domain;

import java.util.concurrent.atomic.AtomicInteger;

/** The Boat class to work with boat's data. */
public class Boat {
  /** Enumeration for boat type. */
  public enum BoatType {
    Sailboat,
    Motorsailer,
    KayakCanoe,
    Other;
  }

  // static id generator shared among all instances of Coordinates
  private static final AtomicInteger idGenerator = new AtomicInteger(1325);

  private double lengthInFeet;
  private double lengthInMeters;
  private int boatId;
  private BoatType type;

  public Boat() {}

  /**
   * An instance of the Boat class.
   *
   * @param type {*} the type of the boat.
   * @param lengthInFeet {*} the length in feet of the boat.
   */
  public Boat(BoatType type, double lengthInFeet) {
    this.type = type;
    this.lengthInFeet = lengthInFeet;
    this.lengthInMeters = getLengthInMeters();
    this.boatId = idGenerator.getAndIncrement() + (int) Math.round(lengthInFeet);
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
