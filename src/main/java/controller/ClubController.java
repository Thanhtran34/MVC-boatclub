package controller;

import controller.exception.InvalidInput;
import java.io.IOException;
import java.util.ArrayList;
import model.domain.Member;
import view.Iconsole;

/** Class for all actions in YatchClub. */
public class ClubController {
  private ArrayList<Member> members;
  private DataController dataController;
  private ImemberController memberController;
  private IboatController boatController;

  /**
   * Method to start the Yatch Club apps.
   *
   * @param ui {*}
   */
  public void runApplication(Iconsole ui) throws InvalidInput, IOException {

    this.members = new ArrayList<>();
    this.dataController = new DataController();
    this.memberController = new MemberController();
    this.boatController = new BoatController();
    ui.showWelcomeMessage();
    this.members = this.dataController.readDataFromFile();
    int selectedItemOfMenu = 0;
    while (selectedItemOfMenu != -1) {
      ui.showMainMenu();
      selectedItemOfMenu = ui.readInputInt();
      switch (selectedItemOfMenu) {
        case 1:
          this.memberController.createMember(ui, this.members);
          this.dataController.saveData(ui, this.members);
          break;
        case 2:
          this.showListMembers(ui);
          break;
        case 3:
          this.memberController.updateMember(ui, this.members);
          this.dataController.saveData(ui, this.members);
          break;
        case 4:
          this.memberController.lookForMember(ui, this.members);
          break;
        case 5:
          this.memberController.deleteMember(ui, this.members);
          this.dataController.saveData(ui, this.members);
          break;
        case 6:
          this.boatController.registerBoat(ui, this.members);
          this.dataController.saveData(ui, this.members);
          break;
        case 7:
          this.boatController.updateBoat(ui, this.members);
          this.dataController.saveData(ui, this.members);
          break;
        case 8:
          this.boatController.deleteBoat(ui, this.members);
          this.dataController.saveData(ui, this.members);
          break;
        case -1:
          this.quitApps(ui);
          break;
        default:
          throw new InvalidInput("Wrong input from user!");
      }
    }
  }

  /** Method to show list of members. */
  private void showListMembers(Iconsole ui) throws InvalidInput {
    ui.listMembers();
    // user choice for format of list
    int selectedList = ui.readInputInt();
    if (selectedList == 1) {
      this.memberController.showCompactList(ui, this.members);
    } else if (selectedList == 2) {
      this.memberController.showVerboseList(ui, this.members);
    } else {
      throw new InvalidInput("Wrong input!");
    }
  }

  /** Method to quit the apps. */
  private void quitApps(Iconsole ui) {
    ui.quitApps();
  }
}
