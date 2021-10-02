package controller;

import java.util.ArrayList;
import model.domain.Member;
import model.persistence.FileHandler;
import model.persistence.Idatastorage;
import view.Iconsole;

/** Module for the YatchClub class. */
public class YatchClub {
  private ArrayList<Member> members;
  private Idatastorage dataStorage;

  /**
   * Method to start the Yatch Club apps.
   *
   * @param ui {*}
   */
  public void runApplication(Iconsole ui) {
    this.members = new ArrayList<Member>();
    this.dataStorage = new FileHandler();
    ui.showWelcomeMessage();
    this.readDataFromFile(ui);
    int selectedItemOfMenu = 0;
    while (selectedItemOfMenu != -1) {
      ui.showMainMenu();
      selectedItemOfMenu = ui.readInputInt();
      switch (selectedItemOfMenu) {
        case 1:
          this.createMember(ui);
          this.saveData(ui);
          break;
        case 2:
          if (this.anyMembers() == false) {
            ui.noMembers();
            break;
          }
          this.showListMembers(ui);
          break;
        case 3:
          if (this.anyMembers() == false) {
            ui.noMembers();
            break;
          }
          this.updateMember(ui);
          this.saveData(ui);
          break;
        case 4:
          if (this.anyMembers() == false) {
            ui.noMembers();
            break;
          }
          this.viewMember(ui);
          this.saveData(ui);
          break;
        case 5:
          if (this.anyMembers() == false) {
            ui.noMembers();
            break;
          }
          this.deleteMember(ui);
          break;
        case 6:
          if (this.anyMembers() == false) {
            ui.noMembers();
            break;
          }
          this.registerBoat(ui);
          this.saveData(ui);
          break;
        case 7:
          if (this.anyMembers() == false) {
            ui.noMembers();
            break;
          }
          this.updateBoat(ui);
          this.saveData(ui);
          break;
        case 8:
          if (this.anyMembers() == false) {
            ui.noMembers();
            break;
          }
          this.deleteBoat(ui);
          this.saveData(ui);
          break;
        case -1:
          this.quit(ui);
          break;
        default:
          ui.notUnderstood();
          break;
      }
    }
  }

  /** Method to register a member. */
  private void createMember(Iconsole ui) {
    String memberName = "";
    String memberPersonalNumber = "";
    // show message to get the user information
    ui.createMember();
    ui.chooseName();
    memberName = ui.readUserInput();
    ui.choosePersonalNo();
    memberPersonalNumber = ui.readUserInput();
    // Check if personalnumber is 12 numbers and only contains numbers.
    if (memberPersonalNumber.matches("\\d+") && memberPersonalNumber.length() == 12) {
      if (this.memberExsists(memberName, memberPersonalNumber)) {
        ui.duplicateInformation();
        return;
      } else {
        this.members.add(new Member(memberName, memberPersonalNumber));
      }
    } else {
      ui.printMessage("Sorry, its not a right personal number! Try again.");
      ui.choosePersonalNo();
    }
  }

  /**
   * Method to save data from user.
   *
   * @param ui
   */
  private void saveData(Iconsole ui) {
    dataStorage.saveMembers(this.members);
  }

  /** Method to show list of members. */
  private void showListMembers(Iconsole ui) {
    ui.listMembers();
    // user choice for format of list
    int selectedList = ui.readInputInt();
    if (selectedList == 1) {
      this.showCompactList(ui);
    } else if (selectedList == 2) {
      this.showVerboseList(ui);
    } else {
      ui.printMessage("Wrong Input. Try again.");
      this.showListMembers(ui);
    }
  }

  /** Method to show the compact list of members. */
  private void showCompactList(Iconsole ui) {
    ui.showCompactList();
  }

  /** Method to show the verbose list of members. */
  private void showVerboseList(Iconsole ui) {
    ui.showVerboseList();
  }

  /** Method to update the member's information. */
  private void updateMember(Iconsole ui) {
    ui.updateMember();
    this.showCompactList(ui);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId)) {

      for (int i = 0; i < this.members.size(); i++) {

        if (this.members.get(i).getMemberId() == memberId) {

          // Update member
          ui.printMessage("Insert new user's information: ");
          ui.chooseName();
          this.members.get(i).changeName(ui.readUserInput());
          ui.choosePersonalNo();
          this.members.get(i).changePersonNo(ui.readUserInput());
          ui.proceedSucessful();
          return;
        }
      }
    } else {
      // Member not found
      ui.proceedFail();
      this.updateMember(ui);
    }
  }

  /**
   * Method to check the valid of the memberid.
   *
   * @param memberIdNo
   * @return {*}
   */
  private boolean validMemberId(String memberIdNo) {
    boolean valid = true;
    // check and validate the memberid
    for (Member member : this.members) {
      if (member.getMemberId().equals(memberIdNo) && memberIdNo.length() == 6) {
        return valid;
      }
    }
    return !valid;
  }
}
