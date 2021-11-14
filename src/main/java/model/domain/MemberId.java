package model.domain;

import java.util.UUID;

/** A class to generate a unique member Id. */
public class MemberId {
  private String memberId;

  public MemberId() {
    UUID id = UUID.randomUUID();
    this.memberId = id.toString().substring(0, 6);
  }
}
