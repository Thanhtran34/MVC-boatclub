package model.domain;

/** The Boat class to work with boat's data. */
public class Boat implements BoatClub{
  public Boat() {
  }

  @Override
  public void accept(BoatClubVisitor visitor) {
    visitor.visit(this);
  }
}
