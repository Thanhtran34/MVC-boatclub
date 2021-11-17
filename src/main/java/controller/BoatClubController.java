package controller;

import controller.exception.BoatNotFound;
import controller.exception.InvalidInput;
import controller.exception.MemberNotFound;
import model.domain.*;
import view.ConsoleUi;
import view.ConsoleUi.ACTIONS;

import java.io.IOException;
import java.util.ArrayList;

/** A class for BoatClub's system controller. */
public class BoatClubController {
  private ArrayList<Member> members = new ArrayList<>();
  private boolean APPLICATION_IS_RUNNING = true;

  /**
   * Call to run the program from the main menu.
   *
   * @param reg The registry to manipulate.
   * @param ui The ui to use for presentation and input.
   */
  public void doMainMenu(MemberRegistry reg, ConsoleUi ui) throws InvalidInput, IOException {
    ConsoleUi.ACTIONS action = ui.showMainMenu();
    //reg.readInformation();
    switch (action) {
      case LIST_COMPACT:
        ui.showCompactList(this.members);
        break;
      case LIST_VERBOSE:
        ui.showVerboseList(this.members);
        break;
      case MEMBER_REGISTER:
        this.m_register(reg, ui);
        reg.saveInformation();
        break;
      case MEMBER_VIEW:
        this.m_view(ui);
        break;
      case MEMBER_EDIT:
        this.m_edit(ui);
        break;
      case MEMBER_DELETE:
        this.m_remove(reg, ui);
        break;
      case BOAT_REGISTER:
        this.b_register(ui);
        break;
      case BOAT_EDIT:
        this.b_edit(ui);
        break;
      case BOAT_REMOVE:
        this.b_remove(ui);
        break;
      case BACK:
        this.doMainMenu(reg, ui);
        break;
      case EXIT:
        if (this.isConfirmed(ui)) {
          APPLICATION_IS_RUNNING = false;
        }
        break;
      default:
        throw new InvalidInput("Invalid Input Parameter!");
    }
  }

  private Member m_register(MemberRegistry reg, ConsoleUi ui) {
    Person personToAdd = ui.presentNewMemberForm(null);

    if (personToAdd != null) {
      return reg.addNewMember(personToAdd);
    }
    return null;
  }

  private void m_view(ConsoleUi ui) {
    ui.lookForOneMember();
    // Show a list of members and select a specific member
    ui.showCompactList(this.members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    for (Member m : members) {
      if (m.getId().equals(memberId)) {
        // Print out member information
        ui.showMemberInfo(m);
        return;
      }
    }
  }

  private void m_edit(ConsoleUi ui) {
    ui.updateMember();
    // Show a list of members and select a specific member
    ui.showCompactList(this.members);
    ui.choosePersonalNo();
    String memberId = ui.readUserInput();
    for (Member m : members) {
      if (m.getId().equals(memberId)) {
        // Update member
        ui.presentUpdateMemberForm(m);
        return;
      }
    }
  }

  private void m_remove(MemberRegistry reg, ConsoleUi ui) {
    ui.deleteMember();
    // Show a list of members and select a specific member
    ui.showCompactList(this.members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    for (Member m : members) {
      if (m.getId().equals(memberId)) {
        reg.removeMember(m);
        ui.proceedSucessful();
        return;
      }
    }
  }

  private void b_register(ConsoleUi ui) {
    ui.registerBoat();
    // Show a list of members and select a specific member
    ui.showCompactList(this.members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId)) {
      // Check boat information
      Boat.BoatType type = this.getBoatTypes(ui);
      ui.chooseBoatLength();
      double boatLength = ui.readInputDoub();
      for (Member m : members) {
        if (m.getId().equals(memberId)) {
          for (Boat boat : m.getBoats()) {
            if (boat.getLength() == boatLength && boat.getType() == type) {
              // Boat found and not unique
              ui.duplicateInformation();
              return;
            }
          }
          // register boat to its owner
          m.addBoat(type, boatLength);
          ui.proceedSucessful();
        }
      }

    } else {
      throw new MemberNotFound("Member is not found!");
    }
  }

  private boolean validMemberId(String memberIdNo) {
    boolean valid = true;
    // check and validate the member id
    for (Member m : members) {
      if (m.getId().equals(memberIdNo) && memberIdNo.length() == 6) {
        return valid;
      }
    }
    return !valid;
  }

  private Boat.BoatType getBoatTypes(ConsoleUi ui) {
    ui.chooseBoatType();
    int counter = 1;
    for (Boat.BoatType type : Boat.BoatType.values()) {
      ui.printMessage(counter + ". " + type.toString());
      counter++;
    }

    counter = ui.readInputInt() - 1;

    // Preventing wrong choice for boat type
    if (counter > 3) {
      counter = 0;
    }

    return Boat.BoatType.values()[counter];
  }

  private void b_edit(ConsoleUi ui) throws BoatNotFound, MemberNotFound {
    ui.updateBoat();
    // Show a list of members and select a specific member
    ui.showCompactList(this.members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId)) {

      for (Member m : members) {

        if (m.getId().equals(memberId)) {
          if (m.getNumberOfBoats() == 0) {
            // No boats registered to member
            ui.noBoats();
            return;
          }
          // Select boat
          int boatId = this.getBoatId(ui, m);
          for (Boat b : m.getBoats()) {
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

  private int getBoatId(ConsoleUi ui, Member member) {
    ui.getListOfBoats(member, member.getBoats());
    ui.chooseBoat();
    return ui.readInputInt();
  }

  private void b_remove(ConsoleUi ui) throws BoatNotFound, MemberNotFound {
    ui.deleteBoat();
    // Show a list of members and select a specific member
    ui.showCompactList(this.members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId)) {

      for (Member m : members) {

        if (m.getId().equals(memberId)) {
          // No boats stored on member

          if (m.getNumberOfBoats() == 0) {

            ui.noBoats();
            return;
          }
          // Select boat
          int boatId = getBoatId(ui, m);
          for (Boat b : m.getBoats()) {

            if (b.getBoatId() == boatId) {

              // Removal of boat
              m.deleteBoat(b);
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

  private boolean isConfirmed(ConsoleUi ui) {
    return ui.getConfirmation();
  }
}
