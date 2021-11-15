package model.domain;

import java.util.UUID;

/** A class to generate a unique member Id. */
public class MemberId {
  private String memberId;

  public MemberId() {}

  public String getMemberId() {
    UUID id = UUID.randomUUID();
    return this.memberId = id.toString().substring(0, 6);
  }
}
