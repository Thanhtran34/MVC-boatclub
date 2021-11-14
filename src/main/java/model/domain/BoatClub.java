package model.domain;

/** An interface for BoatClub element. */
public interface BoatClub {
  void accept(BoatClubVisitor visitor);
}
