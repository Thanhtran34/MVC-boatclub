package controller;

import controller.exception.MemberNotFound;
import java.util.ArrayList;
import java.util.LinkedList;
import model.domain.Boat;
import model.domain.Member;
import view.Iconsole;

/** Method for all CRUD actions with member. */
public class MemberController implements ImemberController {
  @Override
  public void createMember(Iconsole ui, ArrayList<Member> members) {
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
      if (this.checkMemberExistence(memberName, memberPersonalNumber, members)) {
        ui.duplicateInformation();
        return;
      } else {
        members.add(new Member(memberName, memberPersonalNumber));
      }
    } else {
      ui.printMessage("Sorry, it's not a right format of personal number. TRY AGAIN!");
      ui.printMessage("");
      createMember(ui, members);
    }
  }

  @Override
  public void updateMember(Iconsole ui, ArrayList<Member> members) throws MemberNotFound {
    ui.updateMember();
    // Show a list of members and select a specific member
    this.showCompactList(ui, members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId, members)) {
      for (Member m : members) {
        if (m.getMemberId().equals(memberId)) {
          // Update member
          ui.printMessage("Insert new user's information: ");
          ui.chooseName();
          m.changeName(ui.readUserInput());
          ui.choosePersonalNo();
          m.changePersonNo(ui.readUserInput());
          ui.proceedSucessful();
          return;
        }
      }
    } else {
      throw new MemberNotFound("Member is not found!");
    }
  }

  @Override
  public void lookForMember(Iconsole ui, ArrayList<Member> members) throws MemberNotFound {
    ui.lookForOneMember();
    // Show a list of members and select a specific member
    this.showCompactList(ui, members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId, members)) {

      for (Member m : members) {

        if (m.getMemberId().equals(memberId)) {
          // Print out member information
          this.showMemberInfo(ui, m, members);
          return;
        }
      }

    } else {
      throw new MemberNotFound("Member does not exist!");
    }
  }

  @Override
  public void showMemberInfo(Iconsole ui, Member m, ArrayList<Member> members) {
    ui.printMessage(
        "Member ID: "
            + m.getMemberId()
            + "\tName: "
            + m.getName()
            + "\tPersonal Number: "
            + m.getPersonalNumber());
    getListOfBoats(ui, m.getBoats(), members);
  }

  @Override
  public void deleteMember(Iconsole ui, ArrayList<Member> members) throws MemberNotFound {
    ui.deleteMember();
    // Show a list of members and select a specific member
    this.showCompactList(ui, members);
    ui.chooseMemberId();
    String memberId = ui.readUserInput();
    if (this.validMemberId(memberId, members)) {
      for (int i = 0; i < members.size(); i++) {
        if (members.get(i).getMemberId().equals(memberId)) {
          members.remove(i);
          ui.proceedSucessful();
          return;
        }
      }
    } else {
      throw new MemberNotFound("Member does not exist!");
    }
  }

  @Override
  public boolean checkMemberExistence(
      String memberName, String memberPersonalNumber, ArrayList<Member> members) {
    boolean result = true;
    for (Member m : members) {
      if (m.getName().equalsIgnoreCase(memberName)
          && m.getMemberId().equalsIgnoreCase(memberPersonalNumber)) {
        return result;
      }
    }
    return !result;
  }

  @Override
  public boolean validMemberId(String memberIdNo, ArrayList<Member> members) {
    boolean valid = true;
    // check and validate the member id
    for (Member m : members) {
      if (m.getMemberId().equals(memberIdNo) && memberIdNo.length() == 6) {
        return valid;
      }
    }
    return !valid;
  }

  @Override
  public void showCompactList(Iconsole ui, ArrayList<Member> members) {
    StringBuffer compactList = new StringBuffer();
    ui.printMessage("---------------------COMPACT LIST OF ALL MEMBERS----------------------\n");
    if (members.size() != 0) {
      for (Member m : members) {
        compactList.append("Member Id: " + m.getMemberId() + "\n");
        compactList.append("Name: " + m.getName() + "\n");
        compactList.append("Amount of Boats: " + m.getBoats().size() + "\n");
        compactList.append("===================================\n");
      }
    }
    ui.printList(compactList);
  }

  @Override
  public void showVerboseList(Iconsole ui, ArrayList<Member> members) {
    StringBuffer verboseList = new StringBuffer();
    ui.printMessage(
        "--------------------------VERBOSE LIST OF ALL MEMBERS--------------------------\n");
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
    ui.printList(verboseList);
  }

  @Override
  public void getListOfBoats(Iconsole ui, LinkedList<Boat> boats, ArrayList<Member> members) {
    // Printing boats
    if (boats.size() == 0) {
      ui.noBoats();
      lookForMember(ui, members);
    } else {

      ui.printMessage("\tBoats are owned by this member");
      for (Boat boat : boats) {

        ui.printMessage(
            "\t\t Boat ID:"
                + boat.getBoatId()
                + "\tType: "
                + boat.getType()
                + "\t Length in Feet: "
                + boat.getLength());
      }
    }
  }
}
