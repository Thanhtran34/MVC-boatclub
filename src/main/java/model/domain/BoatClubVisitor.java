package model.domain;

/** An interface for BoatClub Visitor. */
public interface BoatClubVisitor {
  void visit(Boat b);
  
  void visit(Member m);

  void postVisit(Member m);
}
