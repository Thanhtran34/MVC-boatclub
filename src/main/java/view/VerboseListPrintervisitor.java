package view;

import model.domain.Boat;
import model.domain.BoatClubVisitor;
import model.domain.Member;

/** A class for verbose list of members. */
public class VerboseListPrintervisitor implements BoatClubVisitor {
  @Override
  public void visit(Member m) {
    System.out.println("A member: " + m.getName());
  }

  @Override
  public void visit(Boat boat) {
    System.out.println("\tBoats owned by this member: " + boat);
  }

  @Override
  public void postVisit(Member m) {}
}
