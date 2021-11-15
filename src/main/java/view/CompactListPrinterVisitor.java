package view;

import model.domain.Boat;
import model.domain.BoatClubVisitor;
import model.domain.Member;

/** A class for compact member list. */
public class CompactListPrinterVisitor implements BoatClubVisitor {
  private int numberOfBoats;

  @Override
  public void visit(Boat b) {
    numberOfBoats = 0;
  }

  @Override
  public void postVisit(Member m) {
    numberOfBoats++;
  }

  @Override
  public void visit(Member member) {
    System.out.println(
        "A member: " + member.getName() + "\n" + " Number of boats: " + numberOfBoats);
  }
}
