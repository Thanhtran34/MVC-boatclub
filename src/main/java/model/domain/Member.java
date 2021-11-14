package model.domain;

import java.util.ArrayList;

/** The Member class to work with member's data. */
public class Member extends Person implements BoatClub {
  private ArrayList<Boat> boatList;
  private MemberId id;

  public Member() {}
  ;

  public Member(Name name, PersonalNumber pernum) {
    super(name, pernum);
    this.id = new MemberId();
    boatList = new ArrayList<>();
    boatList.add(new Boat());
  }

  public Member(Person person) {
    super(person.getName(), person.getPersonalNumber());
    this.id = new MemberId();
    boatList = new ArrayList<>();
    boatList.add(new Boat());
  }

  /**
   * Get the unique member id.
   *
   */
  public MemberId getMemberId() {
    return id;
  }

  @Override
  public void accept(BoatClubVisitor visitor) {
    visitor.visit(this);
    for (Boat boat : boatList) {
      boat.accept(visitor);
    }
    visitor.postVisit(this);
  }
}
