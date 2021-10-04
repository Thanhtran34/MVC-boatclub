package view;

import java.util.Iterator;
import java.util.Scanner;
import model.domain.Boat;
import model.domain.Member;
import model.persistence.MemberList;

/** Module for the ConsoleUi class. */
public class ConsoleUi implements Iconsole {
  private Scanner scan;
  private MemberList listmember = new MemberList();

  public ConsoleUi() {
    scan = new Scanner(System.in);
  }

  @Override
  public void showWelcomeMessage() {
    System.out.println(
        "|____________________________________________________________________________________________|");
    System.out.println(
        "|                                                                                            |");
    System.out.println(
        "|**************************** Welcome to YatchClub ⛵ ⛵ ⛵  **********************************|");
    System.out.println(
        "|                                                                                            |");
    System.out.println(
        "|____________________________________________________________________________________________|");
  }

  @Override
  public void showMainMenu() {
    System.out.println("-----------------------");
    System.out.println("!**** Main Menu ****!");
    System.out.println("-----------------------");
    System.out.println("1. Register new member");
    System.out.println("2. List members");
    System.out.println("3. Update member information");
    System.out.println("4. View member");
    System.out.println("5. Delete member");
    System.out.println("------------------------");
    System.out.println("6. Register boat");
    System.out.println("7. Update boat information\n");
    System.out.println("8. Delete boat");
    System.out.println("------------------------");
    System.out.println("-1. Quit\n");
    System.out.println("------------------------");
    System.out.print("Enter your choice and press enter:\n");
  }

  @Override
  public void createMember() {
    System.out.println("Register new member: ");
  }

  @Override
  public void updateMember() {
    System.out.println("Update an existing member: ");
  }

  @Override
  public void lookForOneMember() {
    System.out.println("Check for a specific member: ");
  }

  @Override
  public void listMembers() {
    System.out.println("Enter your choice for a specific list");
    System.out.println("1. Compact list");
    System.out.println("2. Verbose List");
    System.out.print("Enter your choice and press enter:\n");
  }

  @Override
  public void showCompactList() {
    Iterator<Member> membersList = listmember.getMemberList();
    StringBuffer compactList = new StringBuffer();
    System.out.println(
        "--------------------------Compact List of  all members----------------------------\n");
    while (membersList.hasNext()) {
      Member member = membersList.next();

      compactList.append("Member Id: " + member.getMemberId() + " | ");
      compactList.append("Name: " + member.getName() + " | ");
      compactList.append("Amount of Boats: " + member.getAmountOfBoats() + " | ");
      compactList.append("\n");
    }

    System.out.println(compactList);
  }

  @Override
  public void showVerboseList() {
    Iterator<Member> membersList = listmember.getMemberList();
    StringBuffer verboseList = new StringBuffer();
    System.out.println(
        "-------------------------------Verbose List of all members-------------------------------\n");
    while (membersList.hasNext()) {
      Member member = membersList.next();
      verboseList.append("Member Id: " + member.getMemberId() + " | ");
      verboseList.append("Name: " + member.getName() + " | ");
      verboseList.append("Personal Number: " + member.getPersonalNumber() + " | ");
      verboseList.append("Amount of Boats: " + member.getAmountOfBoats() + " | ");
      verboseList.append("\n");
      int i = 1;
      Iterator<Boat> boatsList = member.getBoatList();
      while (boatsList.hasNext()) {
        Boat boat = boatsList.next();
        verboseList.append(i++);
        verboseList.append("\t Type of Boat: " + boat.getType() + " | ");
        verboseList.append("\t Length of Boat: " + boat.getLength() + " ft");
        verboseList.append("\n");
      }
      verboseList.append("\n");
    }
    System.out.println(verboseList);
  }

  @Override
  public void deleteMember() {
    System.out.println("Delete a member\n");
  }

  @Override
  public void registerBoat() {
    System.out.println("Register a boat for a member");
  }

  @Override
  public void updateBoat() {
    System.out.println("Update boat information");
  }

  @Override
  public void deleteBoat() {
    System.out.println("Delete a boat");
  }

  @Override
  public void quitApps() {
    System.out.println("Yacht Club application terminated \n");
  }

  public String readUserInput() {
    return scan.nextLine();
  }

  @Override
  public int readInputInt() {
    try {
      return Integer.parseInt(this.readUserInput());
    } catch (NumberFormatException | NullPointerException e) {
      return 0;
    }
  }

  @Override
  public double readInputDoub() {
    try {
      return Double.parseDouble(this.readUserInput());
    } catch (NumberFormatException | NullPointerException e) {
      return 0;
    }
  }

  @Override
  public void printMessage(String message) {
    System.out.println(message);
  }

  @Override
  public void proceedSucessful() {
    System.out.println("Action completed!");
  }

  @Override
  public void duplicateInformation() {
    System.out.println("Information is duplicated!");
  }

  @Override
  public void chooseBoat() {}

  @Override
  public void chooseName() {
    System.out.println("Please enter your name: ");
  }

  @Override
  public void choosePersonalNo() {
    System.out.println("Please enter your personal number (Format: YYYYMMDDXXXX): ");
  }

  @Override
  public void chooseMemberId() {
    System.out.println("Please specify your memberID: ");
  }

  @Override
  public void chooseBoatType() {}

  @Override
  public void chooseBoatLength() {}

  @Override
  public void noBoats() {
    System.out.println("Sorry, there has no boat owned by this member. Try again");
  }

  @Override
  public void saveSuccessful() {
    System.out.println("Data has been saved.");
  }
}
