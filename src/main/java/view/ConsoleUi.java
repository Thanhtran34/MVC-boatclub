package view;

import java.util.Scanner;
import model.domain.Boat;
import model.domain.Member;
import model.domain.Name;
import model.domain.Person;
import model.domain.PersonalNumber;

/** The ConsoleUi class to show message and read user input. */
public class ConsoleUi {
  private Scanner scan;

  public enum ACTIONS {
    LIST_COMPACT,
    LIST_VERBOSE,

    MEMBER_VIEW,
    MEMBER_REGISTER,
    MEMBER_EDIT,
    MEMBER_DELETE,

    BOAT_REGISTER,
    BOAT_EDIT,
    BOAT_REMOVE,
    BACK,

    EXIT;
  }

  public ConsoleUi() {
    scan = new Scanner(System.in);
  }

  public ACTIONS showMainMenu() {
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

  private ACTIONS getUserChoice() {
    if (this.readUserInput().equals("1")) {
      return ACTIONS.MEMBER_REGISTER;
    } else if (this.readUserInput().equals("2")) {
      return this.listOfMembers();
    } else if (this.readUserInput().equals("3")) {
      return ACTIONS.MEMBER_EDIT;
    } else if (this.readUserInput().equals("4")) {
      return ACTIONS.MEMBER_VIEW;
    } else if (this.readUserInput().equals("5")) {
      return ACTIONS.MEMBER_DELETE;
    } else if (this.readUserInput().equals("6")) {
      return ACTIONS.BOAT_REGISTER;
    } else if (this.readUserInput().equals("7")) {
      return ACTIONS.BOAT_EDIT;
    } else if (this.readUserInput().equals("8")) {
      return ACTIONS.BOAT_REMOVE;
    } else if (this.readUserInput().equals("-1")) {
      this.quitApps();
      return ACTIONS.EXIT;
    } else {
      return ACTIONS.BACK;
    }
  }

  private ACTIONS listOfMembers() {
    System.out.println("Enter your choice for a specific list");
    System.out.println("1. Compact list");
    System.out.println("2. Verbose List");
    System.out.print("Enter your choice and press enter:\n");
    if (this.readUserInput().equals("1")) {
      return ACTIONS.LIST_COMPACT;
    } else if (this.readUserInput().equals("2")) {
      return ACTIONS.LIST_VERBOSE;
    } else {
      return ACTIONS.EXIT;
    }
  }


  /**
   * Presents the interface for creating a new member.
   *
   * @return A person with all information set.
   */
  public Person presentNewMemberForm(Person defaultInfo) {
    if (defaultInfo == null) {
      System.out.println("Add new member");
      System.out.println("Enter member name:");
      String name = this.readUserInput();
      if (name.length() == 0) {
        return null;
      }

      System.out.println("Enter member personal number:");
      String pnr = this.readUserInput();
      if (pnr.length() == 0) {
        return null;
      }
      return new Person(new Name(name), new PersonalNumber(pnr));
    } else {
      Name n;
      PersonalNumber p;
      System.out.println("Enter member name (" + defaultInfo.getName().toString() + "):");
      String name = this.readUserInput();
      if (name.length() == 0) {
        n = defaultInfo.getName();
      } else {
        n = new Name(name);
      }

      System.out.println(
          "Enter member personal number (" + defaultInfo.getPersonalNumber().getString() + ") :");
      String pnr = this.readUserInput();
      if (pnr.length() == 0) {
        p = defaultInfo.getPersonalNumber();
      } else {
        p = new PersonalNumber(pnr);
      }
      return new Person(n, p);
    }
  }

  /**
   * Presents the interface for creating a new member.
   *
   * @return A person with all information set.
   */
  public void presentUpdateMemberForm(Member m) {
    // Update member
    System.out.println("Insert new user's information: ");
    this.chooseName();
    String name = this.readUserInput();
    this.choosePersonalNo();
    String pnr = this.readUserInput();
    Person per = new Person(new Name(name), new PersonalNumber(pnr));
    m.setInfo(per);
    this.proceedSucessful();
  }

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

  public void getListOfBoats(Member m, Iterable<Boat> boats) {
    // Printing boats
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

  public void lookForOneMember() {
    System.out.println("Check for a specific member: ");
  }

  public void showVerboseList(Iterable<Member> members) {
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

  public void showCompactList(Iterable<Member> members) {
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

  private void quitApps() {
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

  public int readInputInt() {
    try {
      return Integer.parseInt(this.readUserInput());
    } catch (NumberFormatException | NullPointerException e) {
      return 0;
    }
  }

  public double readInputDoub() {
    try {
      return Double.parseDouble(this.readUserInput());
    } catch (NumberFormatException | NullPointerException e) {
      return 0;
    }
  }

  public void printMessage(String message) {
    System.out.println(message);
  }

  public void proceedSucessful() {
    System.out.println("Action completed!");
  }

  public void duplicateInformation() {
    System.out.println("Information is duplicated!");
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

  public boolean getConfirmation() {
    System.out.println("Are you sure you wish to EXIT the Application?: (y/n) ");
    String choice = this.readUserInput();
    if (choice.equals("y")) {
      return true;
    }
    return false;
  }
}
