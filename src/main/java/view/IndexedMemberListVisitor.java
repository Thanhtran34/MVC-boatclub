package view;

import java.util.ArrayList;
import model.domain.Boat;
import model.domain.BoatClubVisitor;
import model.domain.Member;

/** A class for printing out the information when a member is deleted. */
public class IndexedMemberListVisitor implements BoatClubVisitor {
  private int ix = 1;
  ArrayList<Member> members = new ArrayList<>();

  @Override
  public void visit(Member member) {

    System.out.println("" + ix + " Name: " + member.getName());
    ix++;

    members.add(member);
  }

  @Override
  public void visit(Boat boat) {}

  @Override
  public void postVisit(Member member) {}

  public Member getMemberToRemove(ConsoleUi ui) {
    System.out.println("Enter the number of the memmber to remove");
    String number = ui.readInput();
    int readIx = Integer.parseInt(number);

    ix = 1;
    for (Member member : members) {
      if (ix == readIx) {
        return member;
      }
      ix++;
    }

    return null;
  }
}
