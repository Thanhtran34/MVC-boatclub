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
  private double length;
  private int id;
  private BoatType type;

  /**
   * An instance of the Boat class.
   *
   * @param owner {Integer}
   * @param type {*}
   */
  public Boat(String owner, BoatType type, double length) {
    this.owner = owner;
    this.type = type;
    this.length = length;
    this.id = idGenerator.getAndIncrement();
  }

  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public void setType(BoatType type) {
    this.type = type;
  }

  public int getBoatId() {
    return id;
  }

  public BoatType getType() {
    return type;
  }

  /** Method to get length in meters. */
  public double getLengthInMeters() {
    return this.length * 0.3048;
  }
}
