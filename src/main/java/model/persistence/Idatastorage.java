package model.persistence;

import model.domain.Member;

/**
 * An interface for accessing and storing member's data.
 *
 */
public interface Idatastorage {
  public MemberList checkAllMembers();

  public void saveMember(Member mb);
}
