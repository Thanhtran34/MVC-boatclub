package model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Module for the Boat class.
 */
public class Boat {
  /**
   * Enumeration for boat type.
   */
  public enum BoatType {
    Sailboat,
    Motorsailer,
    KayakCanoe,
    Other;
  }

  // static id generator shared among all instances of Coordinates
  private static final AtomicInteger idGenerator = new AtomicInteger(1000);

  private int owner;
  private double length;
  private int id;
  private BoatType type;

  /**
   * An instance of the Boat class.
   *
   * @param owner {Integer}
   * @param type  {*}
   * @param length {double}
   */
  public Boat(int owner, BoatType type, double length) {
    this.owner = owner;
    this.type = type;
    this.length = length;
    this.id = owner + idGenerator.getAndIncrement();
  }

  public int getOwner() {
    return owner;
  }

  public double getLength() {
    return length;
  }

  public int getBoatId() {
    return id;
  }

  public BoatType getType() {
    return type;
  }
}
