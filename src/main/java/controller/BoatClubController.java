package controller;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import controller.exception.BoatNotFound;
import controller.exception.DuplicationFound;
import controller.exception.InvalidInput;
import controller.exception.MemberNotFound;
import model.domain.*;
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
      ConsoleUi.ACTIONS action = ui.showMainMenu();
      switch (action) {
        case LIST_COMPACT:
          ui.showCompactList(reg.getMembers());
          break;
        case LIST_VERBOSE:
          ui.showVerboseList(reg.getMembers());
          break;
        case MEMBER_REGISTER:
          this.m_register(reg, ui);
          reg.saveDataToRegistry();
          break;
        case MEMBER_VIEW:
          this.m_view(reg, ui);
          break;
        case MEMBER_EDIT:
          this.m_edit(reg, ui);
          reg.saveDataToRegistry();
          break;
        case MEMBER_DELETE:
          this.m_delete(reg, ui);
          reg.saveDataToRegistry();
          break;
        case BOAT_REGISTER:
          this.b_register(reg, ui);
          reg.saveDataToRegistry();
          break;
        case BOAT_EDIT:
          this.b_edit(reg, ui);
          reg.saveDataToRegistry();
          break;
        case BOAT_REMOVE:
          this.b_remove(reg, ui);
          reg.saveDataToRegistry();
          break;
        case EXIT:
          doQuit = false;
          ui.quitApps();
          break;
        default:
          throw new InvalidInput("Invalid Input Parameter!");
      }
    }
  }

  private void m_register(MemberRegistry reg, ConsoleUi ui) throws DuplicationFound {
    ui.chooseName();
    String name = ui.readUserInput();
    ui.choosePersonalNo();
    String pnr = ui.readUserInput();
    if (!name.equals(null) && !pnr.equals(null)) {
      reg.registerMember(name, pnr);
    }
    ui.saveSuccessful();
  }

  private void m_view(MemberRegistry reg, ConsoleUi ui) {
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

  private void m_edit(MemberRegistry reg, ConsoleUi ui) throws DuplicationFound {
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

  private void m_delete(MemberRegistry reg, ConsoleUi ui) {
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

  private void b_register(MemberRegistry reg, ConsoleUi ui) {
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

  private void b_edit(MemberRegistry reg, ConsoleUi ui) throws BoatNotFound, MemberNotFound {
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

  private int getBoatId(ConsoleUi ui, Member member) {
    ui.getListOfBoats(member, member.getBoats());
    ui.chooseBoat();
    return ui.readInputInt();
  }

  private void b_remove(MemberRegistry reg, ConsoleUi ui) throws MemberNotFound {
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
          for(int i = 0; i < m.getNumberOfBoats(); i++) {
            if(m.getOneBoat(i).getBoatId() == boatId) {
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
