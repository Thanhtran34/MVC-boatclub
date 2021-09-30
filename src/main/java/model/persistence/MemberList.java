package model.persistence;

import java.util.ArrayList;
import java.util.List;
import model.domain.Member;

/** A class for a list of members. */
public class MemberList {
  private List<Member> memberList = new ArrayList<Member>();

  public MemberList() {
    this.memberList = new ArrayList<Member>();
  }

  public MemberList(List<Member> memberList) {
    this.memberList = memberList;
  }

  public List<Member> getMemberList() {
    return memberList;
  }

  public void setMemberList(List<Member> memberList) {
    this.memberList = memberList;
  }
}
