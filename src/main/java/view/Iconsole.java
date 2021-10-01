package view;

/** A user console interface. */
public interface Iconsole {
  public void showWelcomeMessage();

  public void showMainMenu();

  public void displayFailMessage();

  public void createMember();

  public void updateMember();

  public void viewOneMember();

  public void listMembers();

  public void deleteMember();

  public void registerBoat();

  public void updateBoat();

  public void deleteBoat();

  public void quit();

  public String readUserInput();

  public int readInput();

  public void displayError(String error);

  public void printMessage(String message);

  public void noMembers();

  public void operationFailed();

  public void operationOk();

  public void duplicateInformation();

  public void selectBoat();

  public void selectName();

  public void selectPersonalNo();

  public void selectMemberId();

  public void selectBoatType();

  public void selectBoatLength();

  public void noBoats();
}
