package model.domain;

import java.util.UUID;

/** A class for mmber id. */
public class MemberId {
  private String id;

  public MemberId() {}

  /**
   * Method to get Id.
   * 
   */
  public String getId() {
    UUID idd = UUID.randomUUID();
    this.id = idd.toString().substring(0, 6);
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
