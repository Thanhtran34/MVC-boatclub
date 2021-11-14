package model.domain;

/** An interface for BoatClub Visitor. */
public interface BoatClubVisitor {
  void visit(Member m);

  void postVisit(Member m);

  void visit(Boat b);
}
