package controller;

import java.io.IOException;
import controller.exception.InvalidInput;

/** Responsible for staring the application. */
public class App {
  /**
   * Application starting point.
   *
   * @param args command line arguments.
   * @throws IOException
   */
  public static void main(String[] args) throws InvalidInput, IOException {
    view.Iconsole ui = new view.ConsoleUi();
    controller.ClubController controller = new controller.ClubController();
    controller.runApplication(ui);
  }
}
