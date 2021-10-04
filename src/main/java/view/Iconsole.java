package view;

/** A user console interface. */
public interface Iconsole {
  public void showWelcomeMessage();

  public void showMainMenu();

  public void createMember();

  public void updateMember();

  public void lookForOneMember();

  public void listMembers();

  public void showCompactList();

  public void showVerboseList();

  public void deleteMember();

  public void registerBoat();

  public void updateBoat();

  public void deleteBoat();

  public void quitApps();

  public String readUserInput();

  public int readInputInt();

  public double readInputDoub();

  public void printMessage(String message);

  public void proceedSucessful();

  public void duplicateInformation();

  public void chooseBoat();

  public void chooseName();

  public void choosePersonalNo();

  public void chooseMemberId();

  public void chooseBoatType();

  public void chooseBoatLength();

  public void noBoats();

  public void saveSuccessful();
}
