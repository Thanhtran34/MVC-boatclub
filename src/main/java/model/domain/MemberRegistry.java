package model.domain;

import java.util.ArrayList;

/** A class for CRUD functions with member's data and check if id is unique. */
public class MemberRegistry {
  protected ArrayList<Member> members;

  /** Initializing constructor. */
  public MemberRegistry() {
    members = new ArrayList();
  }

  /**
   * Access to iterate all members.
   *
   * @return the members.
   */
  public Iterable<Member> getMembers() {
    return members;
  }

  /**
   * Removes a member from the registry.
   *
   * @param toBeRemoved The member to be removed.
   */
  public void removeMember(Member toBeRemoved) {
    members.remove(toBeRemoved);
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

    members.add(ret);

    return ret;
  }

  private boolean notUniqueMemberId(MemberId mid) {
    for (Member member : members) {
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
    return members.size();
  }
}
