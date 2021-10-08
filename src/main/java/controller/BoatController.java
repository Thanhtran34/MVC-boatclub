package controller;

import controller.exception.BoatNotFound;
import controller.exception.MemberNotFound;
import java.util.ArrayList;
import java.util.LinkedList;
import model.domain.Boat;
import model.domain.Member;
import view.Iconsole;

/**
 * BoatController class which works with all actions with boat.
 */
public class BoatController implements IboatController {
  private ImemberController memberController = new MemberController();

  @Override
  public void registerBoat(Iconsole ui, ArrayList<Member> members) throws MemberNotFound {
    ui.registerBoat();
    // Show a list of members and select a specific member
    memberController.showCompactList(ui, members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (memberController.validMemberId(memberId, members)) {
      // Check boat information
      Boat.BoatType type = this.getBoatTypes(ui);
      ui.chooseBoatLength();
      double boatLength = ui.readInputDoub();
      for (Member m : members) {

        if (m.getMemberId().equals(memberId)) {
          LinkedList<Boat> boatList = m.getBoats();
          for (Boat boat : boatList) {

            if (boat.getLength() == boatLength && boat.getType() == type) {
              // Boat found and not unique
              ui.duplicateInformation();
              return;
            }
          }
          // register boat to its owner
          boatList.add(new Boat(memberId, type, boatLength));
          ui.proceedSucessful();
        }
      }

    } else {
      throw new MemberNotFound("Member is not found!");
    }
  }

  @Override
  public void updateBoat(Iconsole ui, ArrayList<Member> members) throws MemberNotFound, BoatNotFound {
    ui.updateBoat();
    // Show a list of members and select a specific member
    this.memberController.showCompactList(ui, members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.memberController.validMemberId(memberId, members)) {

      for (Member m : members) {

        if (m.getMemberId().equals(memberId)) {
          if (m.getBoats().size() == 0) {
            // No boats registered to member
            ui.noBoats();
            return;
          }
          // Select boat
          int boatId = this.getBoatId(ui, m, members);
          LinkedList<Boat> boatList = m.getBoats();
          for (Boat b : boatList) {
            if (b.getBoatId() == boatId) {
              b.setType(this.getBoatTypes(ui));
              ui.chooseBoatLength();
              double boatLength = ui.readInputDoub();
              b.setLength(boatLength);
              ui.proceedSucessful();
              return;
            }
          }
          // Boat not found
          throw new BoatNotFound("Boat is not found!");
        }
      }
    } else {
      throw new MemberNotFound("Member is not found!");
    }
  }

  @Override
  public void deleteBoat(Iconsole ui, ArrayList<Member> members) throws MemberNotFound, BoatNotFound {
    ui.deleteBoat();
    // Show a list of members and select a specific member
    this.memberController.showCompactList(ui, members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.memberController.validMemberId(memberId, members)) {

      for (Member m : members) {

        if (m.getMemberId().equals(memberId)) {
          LinkedList<Boat> boatList = m.getBoats();
          // No boats stored on member

          if (boatList.size() == 0) {

            ui.noBoats();
            return;
          }
          // Select boat
          int boatId = getBoatId(ui, m, members);
          for (Boat b : boatList) {

            if (b.getBoatId() == boatId) {

              // Removal of boat
              boatList.remove(b);
              ui.proceedSucessful();
              return;
            }
          }
          // Boat not found
          throw new BoatNotFound("Boat is not found!");
        }
      }
    } else {

      // Member not found
      throw new MemberNotFound("Member is not found!");
    }
  }

  @Override
  public Boat.BoatType getBoatTypes(Iconsole ui) {
    ui.chooseBoatType();
    int counter = 1;
    for (Boat.BoatType type : Boat.BoatType.values()) {
      ui.printMessage(counter + ". " + type.toString());
      counter++;
    }

    counter = ui.readInputInt() - 1;

    // Preventing illegal values
    if (counter > 3) {
      counter = 0;
    }

    return Boat.BoatType.values()[counter];
  }

  @Override
  public int getBoatId(Iconsole ui, Member member, ArrayList<Member> members) {
    this.memberController.getListOfBoats(ui, member.getBoats(), members);
    ui.chooseBoat();
    return ui.readInputInt();
  }
}
