package model.persistence;

import model.domain.Member;

import java.util.ArrayList;

/**
 * An interface for accessing and storing member's data.
 *
 */
public interface Idatastorage {
  public MemberList checkAllMembers();

  public void saveMembers(ArrayList<Member> listmember);
}
