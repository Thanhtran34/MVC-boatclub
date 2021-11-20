package view;

import exception.InvalidInput;
import java.util.ArrayList;
import java.util.Scanner;
import model.domain.Boat;
import model.domain.Member;

/** The ConsoleUi class to show message and read user input. */
public class ConsoleUi {
  private Scanner scan;

  /**
   * Enum for menu actions.
   * 
   */
  public enum Actions {
    List_Compact,
    List_Verbose,

    Member_View,
    Member_Register,
    Member_Edit,
    Member_Delete,

    Boat_Register,
    Boat_Edit,
    Boat_Remove,

    Exit;
  }

  public ConsoleUi() {
    scan = new Scanner(System.in);
  }

  /**
   * Method to get user input.
   * 
   */
  public Actions showMainMenu() throws InvalidInput {
    this.showWelcomeMessage();
    System.out.println("!**** MAIN MENU ****!");
    System.out.println("-----------------------");
    System.out.println("_________Member________");
    System.out.println("1. Register new member");
    System.out.println("2. List of members");
    System.out.println("3. Update member information");
    System.out.println("4. View member");
    System.out.println("5. Delete member");
    System.out.println("------------------------");
    System.out.println("_________Boat________");
    System.out.println("6. Register boat");
    System.out.println("7. Update boat information\n");
    System.out.println("8. Delete boat");
    System.out.println("------------------------");
    System.out.println("-1. Quit\n");
    System.out.println("------------------------");
    System.out.print("Enter your choice and press enter:\n");
    return this.getUserChoice();
  }

  /**
   * Method to show welcome message.
   */
  private void showWelcomeMessage() {
    System.out.println(
        "|____________________________________________________________________________________________|");
    System.out.println(
        "|                                                                                            |");
    System.out.println(
        "|**************************** Welcome to YatchClub ⛵ ⛵ ⛵  *******************************|");
    System.out.println(
        "|                                                                                            |");
    System.out.println(
        "|____________________________________________________________________________________________|");
  }


  /**
   * Check user input and link to ACTIONS.
   * 
   */
  private Actions getUserChoice() throws InvalidInput {
    String userIn = this.readUserInput();
    if (userIn.equals("1")) {
      return Actions.Member_Register;
    } else if (userIn.equals("2")) {
      this.listMembers();
      String choice = this.readUserInput();
      if (choice.equals("1")) {
        return Actions.List_Compact;
      } else if (choice.equals("2")) {
        return Actions.List_Verbose;
      } else {
        return Actions.Exit;
      }
    } else if (userIn.equals("3")) {
      return Actions.Member_Edit;
    } else if (userIn.equals("4")) {
      return Actions.Member_View;
    } else if (userIn.equals("5")) {
      return Actions.Member_Delete;
    } else if (userIn.equals("6")) {
      return Actions.Boat_Register;
    } else if (userIn.equals("7")) {
      return Actions.Boat_Edit;
    } else if (userIn.equals("8")) {
      return Actions.Boat_Remove;
    } else if (userIn.equals("-1")) {
      return Actions.Exit;
    } else {
      throw new InvalidInput("Input is wrong! Try again.");
    }
  }

  /**
   * Method for list of members.
   * 
   */
  private void listMembers() {
    System.out.println("Enter your choice for a specific list");
    System.out.println("1. Compact list");
    System.out.println("2. Verbose List");
    System.out.print("Enter your choice and press enter:\n");
  }

  /**
   * Method to show member's information.
   * 
   */
  public void showMemberInfo(Member m) {
    System.out.println(
        "Member ID: "
            + m.getMemberId()
            + "\tName: "
            + m.getName()
            + "\tPersonal Number: "
            + m.getPersonalNumber());
    this.getListOfBoats(m, m.getBoats());
  }

  /**
   * Method to show list of boats.
   * 
   */
  public void getListOfBoats(Member m, ArrayList<Boat> boats) {
    if (m.getNumberOfBoats() == 0) {
      this.noBoats();
    } else {
      System.out.println("\tBoats are owned by this member");
      for (Boat boat : boats) {

        System.out.println(
            "\t\t Boat ID:"
                + boat.getBoatId()
                + "\tType: "
                + boat.getType()
                + "\t Length in Feet: "
                + boat.getLength());
      }
    }
  }

  /**
   * Method to show verbose list.
   *
   */
  public void showVerboseList(ArrayList<Member> members) {
    StringBuffer verboseList = new StringBuffer();
    System.out.println(
        "--------------------------VERBOSE LIST OF ALL MEMBERS--------------------------\n");
    for (Member m : members) {
      verboseList.append("Member Id: " + m.getMemberId() + "\n");
      verboseList.append("Name: " + m.getName() + "\n");
      verboseList.append("======================================\n");

      int i = 1;
      for (Boat boat : m.getBoats()) {
        verboseList.append(i++);
        verboseList.append("\t Type of Boat: " + boat.getType() + "\n");
        verboseList.append("\t Length of Boat in Feet: " + boat.getLength() + " ft\n");
        verboseList.append(
            "\t Length of Boat in Meters: " + boat.getLengthInMeters() + " meters\n");
        verboseList.append("========================================\n");
      }
      verboseList.append("----------------------------------------------------------------\n");
    }
    System.out.println(verboseList);
  }

  /**
   * Method to show compact list of members.
   * 
   */
  public void showCompactList(ArrayList<Member> members) {
    StringBuffer compactList = new StringBuffer();
    System.out.println("---------------------COMPACT LIST OF ALL MEMBERS----------------------\n");
    for (Member m : members) {
      compactList.append("Member Id: " + m.getMemberId() + "\n");
      compactList.append("Name: " + m.getName() + "\n");
      compactList.append("Amount of Boats: " + m.getNumberOfBoats() + "\n");
      compactList.append("===================================\n");
    }
    System.out.println(compactList);
  }

  /** Method to show type of boat. */
  public Boat.BoatType showBoatTypes() {
    this.chooseBoatType();
    int counter = 1;
    for (Boat.BoatType type : Boat.BoatType.values()) {
      System.out.println(counter + ". " + type.toString());
      counter++;
    }

    counter = this.readInputInt() - 1;

    // Preventing wrong choice for boat type
    if (counter > 3) {
      counter = 0;
    }

    return Boat.BoatType.values()[counter];
  }

  public void lookForOneMember() {
    System.out.println("Check for a specific member: ");
  }

  public void updateMember() {
    System.out.println("Update an existing member: ");
  }

  public void deleteMember() {
    System.out.println("Delete a member\n");
  }

  public void registerBoat() {
    System.out.println("Register a boat for a member: ");
  }

  public void updateBoat() {
    System.out.println("Update boat information: ");
  }

  public void deleteBoat() {
    System.out.println("Delete a boat of one member: ");
  }

  /**
   * Method to show ending of apps.
   * 
   */
  public void quitApps() {
    scan.close();
    System.out.println(
        "______________________________________________________________________________________________");
    System.out.println(
        "******************* Yacht Club application TERMINATED **************************\n");
    System.out.println(
        "______________________________________________________________________________________________\n");
  }

  public String readUserInput() {
    return scan.nextLine();
  }

  /**
   * Method to get int input.
   * 
   */
  public int readInputInt() {
    try {
      return Integer.parseInt(this.readUserInput());
    } catch (NumberFormatException | NullPointerException e) {
      return 0;
    }
  }

  /**
   * Method to get double input.
   * 
   */
  public double readInputDoub() {
    try {
      return Double.parseDouble(this.readUserInput());
    } catch (NumberFormatException | NullPointerException e) {
      return 0;
    }
  }

  public void proceedSucessful() {
    System.out.println("Action completed!");
  }

  public void chooseBoat() {
    System.out.println("Please enter boat ID: ");
  }

  public void chooseName() {
    System.out.println("Please enter your name: ");
  }

  public void choosePersonalNo() {
    System.out.println("Please enter your personal number (Format: YYYYMMDDXXXX): ");
  }

  public void chooseMemberId() {
    System.out.println("Please enter your memberID: ");
  }

  public void chooseBoatType() {
    System.out.println("Please enter boat type: ");
  }

  public void chooseBoatLength() {
    System.out.println("Please enter boat length in feet (Format: 0.0): ");
  }

  public void noBoats() {
    System.out.println("Sorry, there has no boat owned by this member.");
  }

  public void saveSuccessful() {
    System.out.println("Data has been saved.");
  }

  public void duplicateInformation() {
    System.out.println("Information is duplicated!");
  }
}
