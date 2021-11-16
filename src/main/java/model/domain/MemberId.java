package model.domain;

import java.util.UUID;

/** A class for unique id of each member. */
public class MemberId {
  private String memberId;

  public MemberId() {}

  public String getMemberId() {
    UUID id = UUID.randomUUID();
    return this.memberId = id.toString().substring(0, 6);
  }
}
