package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import controller.exception.MemberNotFound;
import model.domain.Boat;
import model.domain.Member;
import view.Iconsole;

/** Interface to group all methods for member controller. */
public interface ImemberController {
  void createMember(Iconsole ui, ArrayList<Member> members);

  void updateMember(Iconsole ui, ArrayList<Member> members) throws MemberNotFound;

  void lookForMember(Iconsole ui, ArrayList<Member> members) throws MemberNotFound;

  void showMemberInfo(Iconsole ui, Member m, ArrayList<Member> members);

  void deleteMember(Iconsole ui, ArrayList<Member> members) throws MemberNotFound;

  boolean checkMemberExistence(
      String memberName, String memberPersonalNumber, ArrayList<Member> members);

  boolean validMemberId(String memberIdNo, ArrayList<Member> members);

  void showCompactList(Iconsole ui, ArrayList<Member> members);

  void showVerboseList(Iconsole ui, ArrayList<Member> members);

  void getListOfBoats(Iconsole ui, LinkedList<Boat> boats, ArrayList<Member> members);
}
