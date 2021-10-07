package controller;

import controller.exception.BoatNotFound;
import controller.exception.InvalidInput;
import controller.exception.MemberNotFound;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import model.domain.Boat;
import model.domain.Boat.BoatType;
import model.domain.Member;
import view.Iconsole;

/** Module for the YatchClub class. */
public class YatchClub {
  private ArrayList<Member> members;
  private DataController dataController;

  /**
   * Method to start the Yatch Club apps.
   *
   * @param ui {*}
   */
  public void runApplication(Iconsole ui) throws InvalidInput, IOException {

    this.members = new ArrayList<>();
    this.dataController = new DataController();
    ui.showWelcomeMessage();
    this.members = this.dataController.readDataFromFile();
    int selectedItemOfMenu = 0;
    while (selectedItemOfMenu != -1) {
      ui.showMainMenu();
      selectedItemOfMenu = ui.readInputInt();
      switch (selectedItemOfMenu) {
        case 1:
          this.createMember(ui);
          this.dataController.saveData(ui, this.members);
          break;
        case 2:
          this.showListMembers(ui);
          break;
        case 3:
          this.updateMember(ui);
          this.dataController.saveData(ui, this.members);
          break;
        case 4:
          this.lookForMember(ui);
          break;
        case 5:
          this.deleteMember(ui);
          this.dataController.saveData(ui, this.members);
          break;
        case 6:
          this.registerBoat(ui);
          this.dataController.saveData(ui, this.members);
          break;
        case 7:
          this.updateBoat(ui);
          this.dataController.saveData(ui, this.members);
          break;
        case 8:
          this.deleteBoat(ui);
          this.dataController.saveData(ui, this.members);
          break;
        case -1:
          this.quitApps(ui);
          break;
        default:
          throw new InvalidInput("Wrong input from user!");
      }
    }
  }

  /** Method to register a member. */
  private void createMember(Iconsole ui) {
    String memberName;
    String memberPersonalNumber;
    // show message to get the user information
    ui.createMember();
    ui.chooseName();
    memberName = ui.readUserInput();
    ui.choosePersonalNo();
    memberPersonalNumber = ui.readUserInput();
    // Check if personal number is 12 numbers and only contains numbers.
    if (memberPersonalNumber.matches("\\d+") && memberPersonalNumber.length() == 12) {
      if (this.checkMemberExistence(memberName, memberPersonalNumber)) {
        ui.duplicateInformation();
      } else {
        this.members.add(new Member(memberName, memberPersonalNumber));
      }
    } else {
      ui.printMessage("Sorry, it's not a right format of personal number. TRY AGAIN!");
      ui.printMessage("");
      this.createMember(ui);
    }
  }

  /** Method to show list of members. */
  private void showListMembers(Iconsole ui) throws InvalidInput {
    ui.listMembers();
    // user choice for format of list
    int selectedList = ui.readInputInt();
    if (selectedList == 1) {
      this.showCompactList(ui, this.members);
    } else if (selectedList == 2) {
      this.showVerboseList(ui, this.members);
    } else {
      throw new InvalidInput("Wrong input!");
    }
  }

  /** Method to show the compact list of members. */
  private void showCompactList(Iconsole ui, ArrayList<Member> members) {
    StringBuffer compactList = new StringBuffer();
    System.out.println("---------------------Compact List of  all members----------------------\n");
    if (members.size() != 0) {
      for (Member m : members) {
        compactList.append("Member Id: " + m.getMemberId() + "\n");
        compactList.append("Name: " + m.getName() + "\n");
        compactList.append("Amount of Boats: " + m.getBoats().size() + "\n");
        compactList.append("===================================\n");
      }
    }
    System.out.println(compactList);
  }

  /** Method to show the verbose list of members. */
  private void showVerboseList(Iconsole ui, ArrayList<Member> members) {
    StringBuffer verboseList = new StringBuffer();
    System.out.println(
        "--------------------------Verbose List of all members--------------------------\n");
    if (members.size() != 0) {
      for (Member m : members) {
        verboseList.append("Member Id: " + m.getMemberId() + "\n");
        verboseList.append("Name: " + m.getName() + "\n");
        verboseList.append("Personal Number: " + m.getPersonalNumber() + "\n");
        verboseList.append("======================================\n");
        int i = 1;
        LinkedList<Boat> boatsList = m.getBoats();
        if (boatsList.size() != 0) {
          for (Boat boat : boatsList) {
            verboseList.append(i++);
            verboseList.append("\t Type of Boat: " + boat.getType() + "\n");
            verboseList.append("\t Length of Boat in Feet: " + boat.getLength() + " ft\n");
            verboseList.append(
                "\t Length of Boat in Meters: " + boat.getLengthInMeters() + " meters\n");
            verboseList.append("========================================\n");
          }
        }
        verboseList.append("----------------------------------------------------------------\n");
      }
    }
    System.out.println(verboseList);
  }

  /** Method to update the member's information. */
  private void updateMember(Iconsole ui) throws MemberNotFound {
    ui.updateMember();
    // Show a list of members and select a specific member
    this.showCompactList(ui, this.members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId)) {
      for (Member m : this.members) {
        if (m.getMemberId().equals(memberId)) {
          // Update member
          ui.printMessage("Insert new user's information: ");
          ui.chooseName();
          m.changeName(ui.readUserInput());
          ui.choosePersonalNo();
          m.changePersonNo(ui.readUserInput());
          ui.proceedSucessful();
        }
      }
    } else {
      throw new MemberNotFound("Member is not found!");
    }
  }

  /**
   * Method to check the valid of the memberid.
   *
   * @param memberIdNo {*}
   * @return {*}
   */
  private boolean validMemberId(String memberIdNo) {
    boolean valid = true;
    // check and validate the member id
    for (Member m : this.members) {
      if (m.getMemberId().equals(memberIdNo) && memberIdNo.length() == 6) {
        return valid;
      }
    }
    return !valid;
  }

  /** Method to look for a specific member. */
  private void lookForMember(Iconsole ui) throws MemberNotFound {
    ui.lookForOneMember();
    // Show a list of members and select a specific member
    this.showCompactList(ui, this.members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId)) {

      for (Member m : this.members) {

        if (m.getMemberId().equals(memberId)) {
          // Print out member information
          this.showMemberInfo(ui, m);
        }
      }

    } else {
      throw new MemberNotFound("Member does not exist!");
    }
  }

  /** Method to show member's profile. */
  private void showMemberInfo(Iconsole ui, Member m) {
    ui.printMessage(
        "MemberID: "
            + m.getMemberId()
            + "\tName: "
            + m.getName()
            + "\tPersonal Number: "
            + m.getPersonalNumber());
    this.getListOfBoats(ui, m.getBoats());
  }

  /** Method to get the list of boats of one member. */
  private void getListOfBoats(Iconsole ui, LinkedList<Boat> boats) {
    // Printing boats
    if (boats.size() == 0) {
      ui.noBoats();
      this.lookForMember(ui);
    } else {

      ui.printMessage("\tBoats are owned by this member");
      for (Boat boat : boats) {

        ui.printMessage(
            "\t\t Boat ID:"
                + boat.getBoatId()
                + "\tType: "
                + boat.getType()
                + "\t Length: "
                + boat.getLength());
      }
    }
  }

  /** Method to delete one specific member. */
  private void deleteMember(Iconsole ui) throws MemberNotFound {
    ui.deleteMember();
    // Show a list of members and select a specific member
    this.showCompactList(ui, this.members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId)) {
      for (Member m : this.members) {
        if (m.getMemberId().equals(memberId)) {
          this.members.remove(m);
          ui.proceedSucessful();
        }
      }
    } else {
      throw new MemberNotFound("Member does not exist!");
    }
  }

  /** Method to register a boat to its owner. */
  private void registerBoat(Iconsole ui) throws MemberNotFound {
    ui.registerBoat();
    // Show a list of members and select a specific member
    this.showCompactList(ui, this.members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId)) {
      // Check boat information
      BoatType type = this.getBoatTypes(ui);
      ui.chooseBoatLength();
      double boatLength = ui.readInputDoub();
      for (Member m : this.members) {

        if (m.getMemberId().equals(memberId)) {
          LinkedList<Boat> boatList = m.getBoats();
          for (Boat boat : boatList) {

            if (boat.getLength() == boatLength && boat.getType() == type) {
              // Boat found and not unique
              ui.duplicateInformation();
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

  /**
   * Method to generate BoatType list.
   *
   * @param ui {*}
   * @return {*}
   */
  private BoatType getBoatTypes(Iconsole ui) {
    ui.chooseBoatType();
    int counter = 1;
    for (BoatType type : BoatType.values()) {
      ui.printMessage(counter + ". " + type.toString());
      counter++;
    }

    counter = ui.readInputInt() - 1;

    // Preventing illegal values
    if (counter > 3) {
      counter = 0;
    }

    return BoatType.values()[counter];
  }

  /** Method to update boat information. */
  private void updateBoat(Iconsole ui) throws MemberNotFound, BoatNotFound {
    ui.updateBoat();
    // Show a list of members and select a specific member
    this.showCompactList(ui, this.members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId)) {

      for (Member m : this.members) {

        if (m.getMemberId().equals(memberId)) {
          if (m.getBoats().size() == 0) {
            // No boats registered to member
            ui.noBoats();
          }
          // Select boat
          int boatId = this.getBoatId(ui, m);
          LinkedList<Boat> boatList = m.getBoats();
          for (Boat b : boatList) {
            if (b.getBoatId() == boatId) {
              b.setType(this.getBoatTypes(ui));
              ui.chooseBoatLength();
              double boatLength = ui.readInputDoub();
              b.setLength(boatLength);
              ui.proceedSucessful();
            } else {
              throw new BoatNotFound("Boat is not found!");
            }
          }
        }
      }
    } else {
      throw new MemberNotFound("Member is not found!");
    }
  }

  /** Method to delete a boat. */
  private void deleteBoat(Iconsole ui) throws MemberNotFound, BoatNotFound {
    ui.deleteBoat();
    // Show a list of members and select a specific member
    this.showCompactList(ui, this.members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId)) {

      for (Member m : this.members) {

        if (m.getMemberId().equals(memberId)) {
          LinkedList<Boat> boatList = m.getBoats();
          // No boats stored on member

          if (boatList.size() == 0) {

            ui.noBoats();
          }
          // Select boat
          int boatId = this.getBoatId(ui, m);
          for (Boat b : boatList) {

            if (b.getBoatId() == boatId) {

              // Removal of boat
              boatList.remove(b);
              ui.proceedSucessful();
              return;
            } else {
              // Boat not found
              throw new BoatNotFound("Boat is not found!");
            }
          }
        }
      }
    } else {

      // Member not found
      throw new MemberNotFound("Member is not found!");
    }
  }

  /**
   * Method to get the id of the boat.
   *
   * @param ui {*}
   * @param member {*}
   * @return {*}
   */
  private int getBoatId(Iconsole ui, Member member) {
    this.getListOfBoats(ui, member.getBoats());
    ui.chooseBoat();
    return ui.readInputInt();
  }

  /** Method to check if the member is already registered in the data storage. */
  private boolean checkMemberExistence(String memberName, String memberPersonalNumber) {
    boolean result = true;
    for (Member m : this.members) {
      if (m.getName().equalsIgnoreCase(memberName)
          && m.getMemberId().equalsIgnoreCase(memberPersonalNumber)) {
        return result;
      }
    }
    return !result;
  }

  /** Method to quit the apps. */
  private void quitApps(Iconsole ui) {
    ui.quitApps();
  }
}
