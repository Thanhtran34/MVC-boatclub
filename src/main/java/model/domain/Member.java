package model.domain;

import java.util.ArrayList;

/** The Member class to work with member's data. */
public class Member extends Person implements BoatClub {

  public Member() {}

  private ArrayList<Boat> ownedBoats;
  private MemberId id;

  Member(Name name, PersonalNumber pnr) {
    super(name, pnr);
    this.id = new MemberId();
    ownedBoats = new ArrayList<>();
    ownedBoats.add(new Boat());
  }

  Member(Person person) {
    super(person.getName(), person.getPersonalNumber());
    this.id = new MemberId();
    ownedBoats = new ArrayList<>();
    ownedBoats.add(new Boat());
  }

  /**
   * Get the unique member id.
   *
   * @return the member id.
   */
  public MemberId getMemberId() {
    return id;
  }

  @Override
  public void accept(BoatClubVisitor v) {
    v.visit(this);
    for (Boat boat : ownedBoats) {
      boat.accept(v);
    }
    v.postVisit(this);
  }
}
