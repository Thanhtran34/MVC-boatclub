package model.domain;

import java.util.ArrayList;

/** A class for CRUD functions with member's data and check if id is unique. */
public class MemberRegistry implements BoatClub {
  private ArrayList<Member> memberList;

  /** Initializing constructor. */
  public MemberRegistry() {
    memberList = new ArrayList();
  }

  /**
   * Removes a member from the registry.
   *
   * @param toBeRemoved The member to be removed.
   */
  public void removeMember(Member toBeRemoved) {
    memberList.remove(toBeRemoved);
  }

  /**
   * Creates a new member and adds it to the registry. A unique member id is assigned.
   *
   * @param person The information to use to create the new member.
   */
  public Member addNewMember(Person person) {
    Member ret = new Member(person);
    MemberId mid = ret.getMemberId();

    while (notUniqueMemberId(mid)) {
      ret = new Member(person);
      mid = ret.getMemberId();
    }

    memberList.add(ret);

    return ret;
  }

  private boolean notUniqueMemberId(MemberId mid) {
    for (Member member : memberList) {
      if (member.getMemberId().equals(mid)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the number of members in the registry.
   *
   * @return the member count.
   */
  public int getMemberCount() {
    return memberList.size();
  }

  @Override
  public void accept(BoatClubVisitor visitor) {
    for (Member member : memberList) {
      member.accept(visitor);
    }
  }
}
