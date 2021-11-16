package controller;

import model.domain.Member;
import model.domain.MemberRegistry;
import view.ConsoleUi;

/** A class for BoatClub's system controller. */
public class BoatClubController {
  /**
   * Call to run the program from the main menu.
   *
   * @param reg The registry to manipulate.
   * @param ui The ui to use for presentation and input.
   */
  public void doMainMenu(MemberRegistry reg, ConsoleUi ui) {

    while (true) {
      ConsoleUi.MainMenuAction action = ui.showMainMenu();
      switch (action) {
        case LIST_ALL_MEMBERS:
          listAllMembers(reg, ui);
          break;
        case ADD_NEW_MEMBER:
          addNewMember(reg, ui);
          break;
        case REMOVE_MEMBER:
          removeMember(reg, ui);
          break;
        default:
          return;
      }
    }
  }

  private void removeMember(MemberRegistry reg, ConsoleUi ui) {
    IndexedMemberListVisitor memberSelector = new IndexedMemberListVisitor();
    reg.accept(memberSelector);

    Member memberToRemove = memberSelector.getMemberToRemove(ui);
    if (memberToRemove != null) {
      reg.removeMember(memberToRemove);
    }
  }

  private void addNewMember(MemberRegistry reg, ConsoleUi ui) {
    Person personToAdd = ui.presentNewMemberForm();

    if (personToAdd != null) {
      reg.addNewMember(personToAdd);
    }
  }

  private void listAllMembers(MemberRegistry reg, ConsoleUi ui) {
    model.domain.BoatClubVisitor mlpv = new CompactListPrinterVisitor();
    reg.accept(mlpv);
  }
}
