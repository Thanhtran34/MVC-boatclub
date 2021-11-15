package controller;

import controller.exception.InvalidInput;
import java.io.IOException;
import model.domain.MemberRegistry;
import view.ConsoleUi;

/** Responsible for staring the application. */
public class App {
  /** Application starting point. */
  public static void main(String[] args) throws InvalidInput, IOException {
    MemberRegistry reg = new MemberRegistry();
    ConsoleUi ui = new ConsoleUi();
    BoatClubController controller = new BoatClubController();

    // c.doSomethingSimple(m, v);

    controller.doMainMenu(reg, ui);
  }
}
