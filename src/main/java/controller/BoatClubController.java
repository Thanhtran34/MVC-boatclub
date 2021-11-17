package controller;

import controller.exception.BoatNotFound;
import controller.exception.DuplicationFound;
import controller.exception.InvalidInput;
import controller.exception.MemberNotFound;
import java.io.IOException;
import model.domain.Boat;
import model.domain.Member;
import model.domain.MemberRegistry;
import view.ConsoleUi;

/** A class for BoatClub's system controller. */
public class BoatClubController {

  public BoatClubController() throws IOException {}

  private boolean doQuit = true;

  /**
   * Call to run the program from the main menu.
   *
   * @param reg The registry to manipulate.
   * @param ui The ui to use for presentation and input.
   */
  public void doMainMenu(MemberRegistry reg, ConsoleUi ui) throws Exception {
    reg.readData();
    while (doQuit) {
      ConsoleUi.Actions action = ui.showMainMenu();
      switch (action) {
        case List_Compact:
          ui.showCompactList(reg.getMembers());
          break;
        case List_Verbose:
          ui.showVerboseList(reg.getMembers());
          break;
        case Member_Register:
          this.registerMember(reg, ui);
          reg.saveDataToRegistry();
          break;
        case Member_View:
          this.viewMember(reg, ui);
          break;
        case Member_Edit:
          this.editMember(reg, ui);
          reg.saveDataToRegistry();
          break;
        case Member_Delete:
          this.deleteMember(reg, ui);
          reg.saveDataToRegistry();
          break;
        case Boat_Register:
          this.registerBoat(reg, ui);
          reg.saveDataToRegistry();
          break;
        case Boat_Edit:
          this.editBoat(reg, ui);
          reg.saveDataToRegistry();
          break;
        case Boat_Remove:
          this.removeBoat(reg, ui);
          reg.saveDataToRegistry();
          break;
        case Exit:
          doQuit = false;
          ui.quitApps();
          break;
        default:
          throw new InvalidInput("Invalid Input Parameter!");
      }
    }
  }

  /**
   * Method to register a member.
   * 
   */
  private void registerMember(MemberRegistry reg, ConsoleUi ui) throws DuplicationFound {
    ui.chooseName();
    String name = ui.readUserInput();
    ui.choosePersonalNo();
    String pnr = ui.readUserInput();
    if (!name.equals(null) && !pnr.equals(null)) {
      reg.registerMember(name, pnr);
    }
    ui.saveSuccessful();
  }

  /**
   * Method to view one member.
   * 
   */
  private void viewMember(MemberRegistry reg, ConsoleUi ui) {
    ui.lookForOneMember();
    // Show a list of members and select a specific member
    ui.showCompactList(reg.getMembers());
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    for (Member m : reg.getMembers()) {
      if (m.getMemberId().equals(memberId)) {
        // Print out member information
        ui.showMemberInfo(m);
      }
    }
  }

  /**
   * Method to update one member.
   * 
   */
  private void editMember(MemberRegistry reg, ConsoleUi ui) throws DuplicationFound {
    ui.updateMember();
    // Show a list of members and select a specific member
    ui.showCompactList(reg.getMembers());
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    for (Member m : reg.getMembers()) {
      if (m.getMemberId().equals(memberId)) {
        // Update member
        ui.chooseName();
        String newName = ui.readUserInput();
        m.setName(newName);
        ui.choosePersonalNo();
        String newPnr = ui.readUserInput();
        m.setPersonalNumber(newPnr);
        if (!newName.equals(null) && !newPnr.equals(null)) {
          ui.saveSuccessful();
        }
      }
    }
  }

  /**
   * Method to delete one member.
   * 
   */
  private void deleteMember(MemberRegistry reg, ConsoleUi ui) {
    ui.deleteMember();
    // Show a list of members and select a specific member
    ui.showCompactList(reg.getMembers());
    ui.chooseMemberId();
    String id = ui.readUserInput();
    for (int i = 0; i < reg.getMemberCount(); i++) {
      if (reg.getMembers().get(i).getMemberId().equals(id)) {
        reg.deleteMember(i);
        ui.proceedSucessful();
      } else {
        throw new MemberNotFound("Member not found!");
      }
    }
  }

  /**
   * Method to register a boat to a member.
   * 
   */
  private void registerBoat(MemberRegistry reg, ConsoleUi ui) {
    ui.registerBoat();
    // Show a list of members and select a specific member
    ui.showCompactList(reg.getMembers());
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId, reg)) {
      // Check boat information
      Boat.BoatType type = this.getBoatTypes(ui);
      ui.chooseBoatLength();
      double boatLength = ui.readInputDoub();
      for (Member m : reg.getMembers()) {
        if (m.getMemberId().equals(memberId)) {
          for (Boat boat : m.getBoats()) {
            if (boat.getLength() == boatLength && boat.getType() == type) {
              // Boat found and not unique
              ui.duplicateInformation();
              return;
            }
          }
          // register boat to its owner
          m.registerBoat(memberId, type, boatLength);
          ui.proceedSucessful();
        }
      }

    } else {
      throw new MemberNotFound("Member is not found!");
    }
  }

  /**
   * Method to check if member id is right format.
   * 
   */
  private boolean validMemberId(String memberIdNo, MemberRegistry reg) {
    boolean valid = true;
    // check and validate the member id
    for (Member m : reg.getMembers()) {
      if (m.getMemberId().equals(memberIdNo) && memberIdNo.length() == 6) {
        return valid;
      }
    }
    return !valid;
  }

  /**
   * Method to get type of boat.
   * 
   */
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



  /**
   * Method to update one boat.
   * 
   */
  private void editBoat(MemberRegistry reg, ConsoleUi ui) throws BoatNotFound, MemberNotFound {
    ui.updateBoat();
    // Show a list of members and select a specific member
    ui.showCompactList(reg.getMembers());
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId, reg)) {

      for (Member m : reg.getMembers()) {

        if (m.getMemberId().equals(memberId)) {
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
 
  /**
   * Method to get id of one boat.
   * 
   */
  private int getBoatId(ConsoleUi ui, Member member) {
    ui.getListOfBoats(member, member.getBoats());
    ui.chooseBoat();
    return ui.readInputInt();
  }

  /**
   * Method to delete one boat.
   * 
   */
  private void removeBoat(MemberRegistry reg, ConsoleUi ui) throws MemberNotFound {
    ui.deleteBoat();
    // Show a list of members and select a specific member
    ui.showCompactList(reg.getMembers());
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId, reg)) {

      for (Member m : reg.getMembers()) {

        if (m.getMemberId().equals(memberId)) {
          // No boats stored on member
          if (m.getNumberOfBoats() == 0) {
            ui.noBoats();
          }
          // Select boat
          int boatId = getBoatId(ui, m);
          for (int i = 0; i < m.getNumberOfBoats(); i++) {
            if (m.getOneBoat(i).getBoatId() == boatId) {
              m.deleteBoat(i);
              ui.proceedSucessful();
            }
          }
        }
      }
    } else {

      // Member not found
      throw new MemberNotFound("Member is not found!");
    }
  }
}
