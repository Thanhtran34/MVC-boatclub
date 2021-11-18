package model.domain;

import exception.DuplicationFound;
import exception.MemberNotFound;
import java.io.IOException;
import java.util.ArrayList;
import model.persistence.FileHandler;
import model.persistence.IdataStorage;

/** A class for CRUD functions with member's data and check if id is unique. */
public class MemberRegistry {
  private ArrayList<Member> members = new ArrayList<>();
  private IdataStorage data;

  /** Initializing constructor. */
  public MemberRegistry() {
    data = new FileHandler();
  }

  public ArrayList<Member> readData() throws IOException {
    this.members = data.checkAllMembers();
    return this.members;
  }

  public void saveDataToRegistry() throws IOException {
    data.saveMembers(members);
  }

  /**
   * Access to iterate all members.
   *
   * @return the members.
   */
  public ArrayList<Member> getMembers() {
    return members;
  }

  /** Creates a new member and adds it to the registry. A unique member id is assigned. */
  public void addMember(String name, String pnr) throws DuplicationFound {
    MemberId id = new MemberId();
    Member m = new Member(name, pnr, id.getId());
    if (!notUniqueMemberId(m.getMemberId())) {
      members.add(m);
    } else {
      throw new DuplicationFound("Member Id is not unique!");
    }
  }

  private boolean notUniqueMemberId(String input) {
    for (Member member : members) {
      if (member.getMemberId().equals(input)) {
        return true;
      }
    }
    return false;
  }

  /** Method to check if member exists. */
  public boolean isMemberExist(String id) {
    for (Member member : members) {
      if (member.getMemberId().equals(id)) {
        return true;
      }
    }
    return false;
  }

  /** Removes a member from the registry. */
  public void removeMember(int idx) throws MemberNotFound {
    this.members.remove(idx);
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
