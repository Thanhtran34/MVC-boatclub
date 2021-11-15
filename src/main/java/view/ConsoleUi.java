package view;

import model.domain.MemberId;
import model.domain.Name;
import model.domain.Person;
import model.domain.PersonalNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/** The ConsoleUi class to show message and read user input. */
public class ConsoleUi {
  /** Represents the actions in the main menu. */
  public enum MainMenuAction {
    LIST_ALL_MEMBERS,
    ADD_NEW_MEMBER,
    REMOVE_MEMBER,
    QUIT
  }

  // }

  /**
   * Presents the application main menu.
   *
   * @return The otpion the user chooses.
   */
  public MainMenuAction showMainMenu() {
    this.showWelcomeMessage();
    System.out.println("Main menu");
    System.out.println("1. Add a new member.");
    System.out.println("2. List all members.");
    System.out.println("3. Remove a member.");

    System.out.println("q. to quit.");

    String kbd = readInput();
    if (kbd.equals("2")) {
      return MainMenuAction.LIST_ALL_MEMBERS;
    } else if (kbd.equals("1")) {
      return MainMenuAction.ADD_NEW_MEMBER;
    } else if (kbd.equals("3")) {
      return MainMenuAction.REMOVE_MEMBER;
    }

    return MainMenuAction.QUIT;
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

  /**
   * Presents the interface for creating a new member.
   *
   * @return A person with all information set.
   */
  public Person presentNewMemberForm() {
    System.out.println("Add new member");
    System.out.println("Enter member name:");
    String name = readInput();

    System.out.println("Enter member personal number:");
    String pnr = readInput();

    return new Person(new Name(name), new PersonalNumber(pnr));
  }

  public String readInput() {
    try {
      BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in, "Cp850"));

      try {
        return keyboard.readLine();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return "";
  }

  public String zeroPadd(MemberId id) {
    String[] zeroPadding = {"X", "00000", "0000", "000", "00", "0", ""};
    String strId = id.getMemberId();

    return zeroPadding[strId.length()] + strId;
  }
}
