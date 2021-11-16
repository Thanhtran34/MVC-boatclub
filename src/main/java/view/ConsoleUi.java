package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Scanner;

import model.domain.Boat;
import model.domain.MemberId;
import model.domain.Name;
import model.domain.PersonalNumber;

/** The ConsoleUi class to show message and read user input. */
public class ConsoleUi {
  private Scanner scan;

  public ConsoleUi() {
    scan = new Scanner(System.in);
  }

  public void showWelcomeMessage() {
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

  public void showMainMenu() {
    System.out.println("");
    System.out.println("!**** MAIN MENU ****!");
    System.out.println("-----------------------");
    System.out.println("_________Member________");
    System.out.println("1. Register new member");
    System.out.println("2. List members");
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
  }

  public void createMember() {
    System.out.println("Register new member: ");
  }

  public void updateMember() {
    System.out.println("Update an existing member: ");
  }

  public void lookForOneMember() {
    System.out.println("Check for a specific member: ");
  }

  public void listMembers() {
    System.out.println("Enter your choice for a specific list");
    System.out.println("1. Compact list");
    System.out.println("2. Verbose List");
    System.out.print("Enter your choice and press enter:\n");
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
        ArrayList<Boat> boatsList = m.getBoats();
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
    System.out.println(verboseList);
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

  public void quitApps() {
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

  public void printList(StringBuffer list) {
    System.out.println(list);
  }
}
