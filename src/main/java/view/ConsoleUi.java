package view;

import java.util.Scanner;

/** Module for the ConsoleUi class. */
public class ConsoleUi implements Iconsole {
  private Scanner scan = new Scanner(System.in);

  @Override
  public void showWelcomeMessage() {
    System.out.println("|____________________________________________________________________________________________|");
    System.out.println("|                                                                                            |");
    System.out.println(
        "|**************************** Welcome to YatchClub ⛵ ⛵ ⛵  **********************************|");
    System.out.println("|                                                                                            |");
    System.out.println("|____________________________________________________________________________________________|");
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
    System.out.println("0. Quit\n");
    System.out.println("------------------------");
    System.out.print("Enter your choice and press enter:\n");
  }

  @Override
  public void displayFailMessage() {
    System.out.println("Oops, something went wrong with your choice. Please try again!");
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
  public void viewOneMember() {
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
  public void quit() {
    System.out.println("Yacht Club application terminated \n");
  }

  @Override
  public void displayError(String error) {
    System.out.println("Error:\n" + error + "\n");
  }

  @Override
  public String readUserInput() {
    return scan.nextLine();
  }

  @Override
  public int readInput() {
    return scan.nextInt();
  }

  @Override
  public void printMessage(String message) {}

  @Override
  public void noMembers() {}

  @Override
  public void operationFailed() {}

  @Override
  public void operationOk() {}

  @Override
  public void duplicateInformation() {}

  @Override
  public void selectBoat() {}

  @Override
  public void selectName() {}

  @Override
  public void selectPersonalNo() {}

  @Override
  public void selectMemberId() {}

  @Override
  public void selectBoatType() {}

  @Override
  public void selectBoatLength() {}

  @Override
  public void noBoats() {}
}
