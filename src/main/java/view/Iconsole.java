package view;

/** A user console interface. */
public interface Iconsole {
  void showWelcomeMessage();

  void showMainMenu();

  void createMember();

  void updateMember();

  void lookForOneMember();

  void listMembers();

  void showCompactList();

  void showVerboseList();

  void deleteMember();

  void registerBoat();

  void updateBoat();

  void deleteBoat();

  void quitApps();

  String readUserInput();

  int readInputInt();

  double readInputDoub();

  void printMessage(String message);

  void proceedSucessful();

  void duplicateInformation();

  void chooseBoat();

  void chooseName();

  void choosePersonalNo();

  void chooseMemberId();

  void chooseBoatType();

  void chooseBoatLength();

  void noBoats();

  void saveSuccessful();
}
